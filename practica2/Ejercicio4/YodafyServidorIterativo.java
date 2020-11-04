import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class YodafyServidorIterativo {

	public static void main(String[] args) {

		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		byte []buffer=new byte[256];
		// Número de bytes leídos
		int bytesLeidos=0;

		DatagramSocket socketServidor;

		DatagramPacket paquete;

		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			socketServidor = new DatagramSocket(port);

			paquete = new DatagramPacket(buffer,buffer.length);
			//////////////////////////////////////////////////

			// Mientras ... siempre!
			do {

				ProcesadorYodafy procesador = new ProcesadorYodafy (socketServidor);
				procesador.procesa();

			} while (true);

			// socketServidor.close();

		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
