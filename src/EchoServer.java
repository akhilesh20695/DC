import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException
	{
		int port=10000;
		if(args.length>0)
		{
			port=Integer.parseInt(args[0]);
		}
		ServerSocket serversocket=new ServerSocket(port);
		System.out.println("Echo Server is running");
		
		System.out.println("Waiting For client to connect");
		Socket socket=serversocket.accept();
	
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String message=new String();
		while(!(message=reader.readLine()).equals(".bye"))
		{
			System.out.println(message);
		}
		System.out.println("Shutting Down Server..!!");
		reader.close();
		serversocket.close();
	}
}
