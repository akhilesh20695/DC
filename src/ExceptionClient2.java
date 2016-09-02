import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ExceptionClient2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		String hostname="localhost";
		int port=8000;
		Socket socket=new Socket(hostname,port);
		System.out.println("Connected to server..Waiting for server to respond");

		System.out.println("Will wait for 5 seconds before timeout..");
		BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String result=reader.readLine();
		System.out.println(result);
		socket.close();
	}

}
