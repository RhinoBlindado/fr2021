import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;
import java.util.*;

import javax.lang.model.util.ElementScanner6;

import java.time.LocalDateTime;


public class ProcesadorReloj extends Thread {
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private Socket socketServicio;

	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorReloj(Socket socketServicio) {this.socketServicio=socketServicio;}

	// PrintWriter outPrinter  = new PrintWriter(socketServicio.getOutputStream(),true);
	// BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));

	// Aquí es donde se realiza el procesamiento realmente:
	@Override
	public void run(){

		
		try {
			System.out.println("---SERVIDOR DE ESCUCHA ALARMAS INICIADO---");
			System.out.println(">ESCUCHANDO");
			Boolean Salir = false;
			ArrayList<Alarma> alarmas = new ArrayList<Alarma> ();
			PrintWriter    outPrinter = new PrintWriter(socketServicio.getOutputStream(),true);
			BufferedReader inReader   = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));

			System.out.println(">CONEXION ESTABLECIDA");

			while(!Salir){

					String peticion = new String();
					System.out.println(">ESPERANDO LECTURA");
					peticion = inReader.readLine();
					int code = 0;

					try{
						System.out.println(">RECIBIDO "+peticion.substring(1,4));
						code = Integer.parseInt(peticion.substring(1,4));
					} catch(Exception e){
						System.err.println(">RECIBIDO: ERROR SINTAXIS");
						// code = 0;
					}

					// Reloj reinterpreta el mensaje:
					switch (code){
						case 100: //#100 Crear Alarma hh:mm
							System.out.println("	>>["+code+"]: CREAR ALARMA");
							
							try
							{
								int hora = Integer.parseInt(peticion.substring(18,20));
								int min = Integer.parseInt(peticion.substring(21,23));
	
								//Insertamos si es una hora
								if(hora < 24 && hora >= 0 && min < 60 && min >= 0){
									alarmas.add(new Alarma(hora,min));
									Collections.sort(alarmas); // Importante que esten ordenadas
									outPrinter.println("#001 OK");
								}
								else
								{
									System.out.println("	>>["+code+"]: ERROR DE SINTAXIS");
									outPrinter.println("BAD");
								}
							}
							catch (Exception e)
							{
								System.out.println("	>>["+code+"]: ERROR DE SINTAXIS");
								outPrinter.println("BAD");
							}
							break;


						case 101: //#101 Borrar Alarma i
							System.out.println("	>>["+code+"]: BORRAR ALARMA");
							try
							{
								int alarma = Integer.parseInt(peticion.substring(19,peticion.length()));
								alarmas.remove(alarma);
								System.out.println("	>>["+code+"]: OK");

							}
							catch(Exception e)
							{
								System.out.println("	>>["+code+"]: ERROR DE SINTAXIS");
							}
							break;

						case 102:	//#102 Listar Alarmas
							System.out.println("	>>["+code+"]: LISTAR ALARMAS");
							String lista = new String ();
							for(int i=0; i<alarmas.size(); i++) lista += "Alarma " + i + ":\t" + alarmas.get(i).toString() + "::";
							System.out.println(lista);
							outPrinter.println(lista);
							break;

						case 200: //#200 Activar Alarmas
							System.out.println("	>>["+code+"]: ACTIVAR ALARMAS");
							try {
								for(Alarma al : alarmas){
									LocalDateTime ahora = LocalDateTime.now();
									int segs_dia = ahora.getHour()*3600 + ahora.getMinute()*60 + ahora.getSecond();
									int segs_al  = al.getSegundoAlarma();
									int t_sleep  = 0;

									if(segs_dia<segs_al) t_sleep = segs_al - segs_dia;
									outPrinter.println(">SERVIDOR: Empieza una alarma");
									Thread.sleep(t_sleep*1000);
									outPrinter.println(">SERVIDOR: Notificacion Alarma - !!Despierta!!");
								}
								alarmas.clear();
								outPrinter.println("#001 OK");
							} catch (InterruptedException e) {System.err.println(">SERVIDOR: ERROR");}
							break;

						case 300: //#300 Activar Temporizador s
							System.out.println("	>>["+code+"]: ACTIVAR TEMPORIZADOR");
							try
							{
									int tam = peticion.length();
									int seg = Integer.parseInt(peticion.substring(26,tam));
								try 
								{
									Thread.sleep(seg*1000);
									outPrinter.println("SERVIDOR: Notificacion Temporizador: Ya han pasado " + seg + " segundos.");
								}
								catch (InterruptedException e) {System.err.println("    >>["+code+"]: ERROR");}
							}
							catch (Exception e)
							{
								System.out.println("	>>["+code+"]: ERROR DE SINTAXIS");
								outPrinter.println("ERROR");
							}
							break;														

						case 400:	//#400 Salir
							System.out.println("	>>["+code+"]: SALIR");
							outPrinter.println("#001 OK");
							Salir = true;
							break;

						default:
							System.out.println(code);
							outPrinter.println(">Error en la sintaxis. " + peticion + " no encontrada.");
						break;
						
					}

					System.out.println(">Alarmas del sistema:");
					String lista = new String ();
					for(int i=0; i<alarmas.size(); i++) lista += "Alarma " + i + ":\t" + alarmas.get(i).toString() + " :: ";
					System.out.println(lista);
					System.out.println("------------------------------\n");
				}
			} catch (IOException e) {
				System.err.println(">Error al obtener los flujos de entrada/salida.");
			}
			System.out.println(">FINALIZANDO HILO");

	}

}
