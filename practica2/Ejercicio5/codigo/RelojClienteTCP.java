import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.util.*;

public class RelojClienteTCP {

	public static void main(String[] args) {
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		// Socket para la conexión TCP
		Socket socketServicio=null;
		PrintWriter outPrinter = null;
		BufferedReader inReader = null;
        Scanner scan = new Scanner(System.in);

		try {
			socketServicio = new Socket (host,port);

			//[EJ2]
			outPrinter = new PrintWriter(socketServicio.getOutputStream(),true);
			inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));

			boolean corriendo 	= true;
			int input;

			while(corriendo)
			{
				System.out.println(	"---CLIENTE DE ALARMA EN RED---\n"+
									">Seleccionar:\n"+
									"1 - Crear Alarma\n"+
									"2 - Listar Alarmas\n"+
									"3 - Activar Alarma\n"+
									"4 - Borrar Alarma\n"+
									"5 - Activar Temporizador\n"+
									"0 - Salir\n"+
									"Seleccion: ");

				try
				{
					input = scan.nextInt();
				}
				catch(Exception e)
				{
					System.err.println(">CLIENTE: ENTRADA ERRONEA");
					input = -1;
				}

				switch(input)
				{
					// 1 - Crear Alarma
					case 1:
						System.out.println(	"--CREAR ALARMA--\n"+
											">>Hora (HH):");
						String hora = scan.next();
						
						System.out.println(">>Minuto (MM):");
						String minuto = scan.next();

						outPrinter.println("#100 Crear Alarma "+hora+":"+minuto);
						if(inReader.readLine().equals("#001 OK"))
						{
							System.out.println(">>ALARMA CREADA");
						}
						else
						{
							System.out.println(">>ERROR, VUELVA A INTENTAR.");
						}
					break;

					// 2 - Listar Alarmas
					case 2:
						System.out.println(	">>LISTAR ALARMAS: ");
						outPrinter.println("#102 Listar Alarmas");
						System.out.println(inReader.readLine());
					break;

					// 3 - Activar Alarma
					case 3:
						System.out.println(	">>ACTIVAR ALARMAS: ");
						outPrinter.println("#200 Activar Alarmas");
						String str = new String();
						do{
							str = inReader.readLine();
							System.out.println(str);
						}while(!str.equals("#001 OK"));

					break;

					// 4 - Borrar Alarma
					case 4:
						System.out.println(	">>BORRAR ALARMA: ");

						outPrinter.println("#102 Listar Alarmas");
						System.out.println(inReader.readLine());

						System.out.println(">>Seleccion: ");
						String numAlarm = scan.next();

						outPrinter.println("#101 Borrar Alarma "+numAlarm);
					break;

					// 5 - Activar Temporizador
					case 5:
						System.out.println(	">>ACTIVAR TEMPORIZADOR: ");
						System.out.println( ">>Ingresar tiempo (segundos): ");
						String segs = scan.next();
						outPrinter.println("#300 Activar Temporizador "+segs);
						System.out.println(inReader.readLine());
					break;

					// 0 - Salir
					case 0:
						outPrinter.println("#400 Salir");
						if(inReader.readLine().equals("#001 OK"))
						{
							corriendo=false;
							socketServicio.close();
						}

					break;

					default:
						System.out.println(">>ERROR: SELECCION INCORRECTA");
						scan.nextLine();
					break;
				}

				System.out.println("------------------------------\n");
			}

/* 
			while(true){
				String entrada = new String();
				Scanner sc = new Scanner(System.in);
				entrada = sc.nextLine();
				outPrinter.println(entrada);
				System.out.println(inReader.readLine());
			}
*/


		} catch (UnknownHostException e) {
			System.err.println(">CLIENTE: Error, nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println(">CLIENTE: Error de entrada/salida al abrir el socket.");
		}
	}
}
