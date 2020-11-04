import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class YodafyClienteUDP {

	public static void main(String[] args) {

		byte []buferEnvio;
		// byte []buferRecepcion=new byte[256];
		int bytesLeidos=0;

		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;

		// Socket para la conexión UDP
		DatagramSocket socket;
		// DatagramPacket paquete;
		InetAddress direccion;


		try {
			socket = new DatagramSocket();
			direccion = InetAddress.getByName(host);

			buferEnvio="Al monte del volcan debes ir sin demora".getBytes();


			DatagramPacket paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
			socket.send(paquete);

			System.out.println("Paquete enviado");

			socket.receive(paquete);

			System.out.println("Paquete recibido: ");
			for(int i=0;i<paquete.getData().length;i++){
				// System.out.print("hola");
				System.out.print((char)paquete.getData()[i]);
			}

			socket.close();

			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
