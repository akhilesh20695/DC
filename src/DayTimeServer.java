import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServer {

	public static void main(String[] args) throws IOException
	{
		int port=10000;
		ServerSocket serversocket=new ServerSocket(port);
		System.out.println("DayTime server is running");
		
		System.out.println("Waiting For client to connect");
		Socket socket=serversocket.accept();
	
		
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		Date date=new Date();
		writer.write(date.toString());
		writer.flush();
		writer.close();
		serversocket.close();
	}
}
