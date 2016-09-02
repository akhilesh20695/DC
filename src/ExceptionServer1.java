import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ExceptionServer1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int port=10000;
		ServerSocket serversocket=null;
		try
		{
			serversocket=new ServerSocket(port);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		Socket socket=null;
		try {
			socket=serversocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client Connected");
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
	}

}
