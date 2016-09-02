import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DayTimeClient {
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		String hostname="localhost";
		int port=10000;
		Socket socket=new Socket(hostname,port);
		System.out.println("Connection Established");
		socket.setSoTimeout(2000); // Timeout just in case the server stalls
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println(reader.readLine());
		
		reader.close();
		socket.close();
	}

}
