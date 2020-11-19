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

		try {
			socketServicio = new Socket (host,port);

			//[EJ2]
			outPrinter = new PrintWriter(socketServicio.getOutputStream(),true);
			inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));

			while(true){
				String entrada = new String();
				Scanner sc = new Scanner(System.in);
				entrada = sc.nextLine();
				outPrinter.println(entrada);
				System.out.println(inReader.readLine());
			}

			// socketServicio.close();

		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
