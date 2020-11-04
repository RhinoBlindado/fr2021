import java.io.IOException;
import java.util.Random;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ProcesadorYodafy {
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private DatagramSocket socketServicio;
	// stream de lectura (por aquí se recibe lo que envía el cliente)
	private DatagramPacket paquete;
	// stream de escritura (por aquí se envía los datos al cliente)

	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;

	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorYodafy(DatagramSocket socketServicio) {
		this.socketServicio=socketServicio;
		random=new Random();
	}


	// Aquí es donde se realiza el procesamiento realmente:
	void procesa(){
		byte [] datosRecibidos=new byte[1024];
		byte [] datosEnviar;
		paquete = new DatagramPacket(datosRecibidos,datosRecibidos.length);

		try {
			// Lee la frase a Yodaficar:
			////////////////////////////////////////////////////////
			socketServicio.receive(paquete);
			////////////////////////////////////////////////////////

			// Yoda hace su magia:
			// Creamos un String a partir de un array de bytes de tamaño "bytesRecibidos":
			String peticion=new String(datosRecibidos,0,datosRecibidos.length);
			// Yoda reinterpreta el mensaje:
			String respuesta=yodaDo(peticion);
			// Convertimos el String de respuesta en una array de bytes:
			datosEnviar=respuesta.getBytes();


			paquete = new DatagramPacket(datosEnviar, datosEnviar.length, paquete.getAddress(), paquete.getPort());

			socketServicio.send(paquete);


		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";

		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];

			s[j]=s[k];
			s[k]=tmp;
		}

		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}

		return resultado;
	}
}
