import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ExceptionServer2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int port=8000;
		System.out.println("Starting Server..!!");
		ServerSocket serversocket=null;
		try
		{
			serversocket=new ServerSocket(port);
		}
		catch(BindException e)
		{
			System.out.println("The port/address is already used by some other program");
			System.out.println("Cannot start server. change the port and try again..!!");
			System.out.println("Exitting..!!");
			System.exit(0);
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
