import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoMultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		String hostname="localhost";
		int port=10000;
		if(args.length>0)
		{
			hostname=args[0];
			port=Integer.parseInt(args[1]);
		}
		System.out.println("Welcome to Simple Echo Client-server program");
		System.out.print("Connecting to server...!!!");
		Socket socket=new Socket(hostname,port);
		System.out.println("Connection Established");
		System.out.println("Enter Something and it will reflect in the server(.bye to exit)");
		Scanner sc=new Scanner(System.in);
		int choice=0;
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

		while(choice!=1)
		{
			System.out.print("Enter your message: ");
			String message=sc.nextLine();
			System.out.print("\n");
			if(message.equals(".bye"))
			{
				writer.write(message);
				writer.newLine();
				writer.flush();
				writer.close();
				sc.close();
				socket.close();
				choice=1;
				System.out.println("Exiting the Application");
			}
			else
			{
				writer.write(message);
				writer.newLine();
				writer.flush();
				String response=reader.readLine();
				System.out.println(response);
			}
		}
		
	}
}
