import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class RelojServidorConcurrente {

	public static void main(String[] args) {

		// Puerto de escucha
		int port=8989;

		// Número de bytes leídos
		int bytesLeidos=0;

		ServerSocket socketServidor;
		Socket socketConexion = null;

		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			socketServidor = new ServerSocket(port);

			do {

				// Aceptamos una nueva conexión con accept()
				socketConexion = socketServidor.accept();

				ProcesadorReloj procesador = new ProcesadorReloj(socketConexion);
				procesador.start();

			} while (true);

		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
