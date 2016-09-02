import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MathClient 
{
	public static void main(String [] args){
		String hostname = "localhost";
		int port = 10000;
		try 
		{
			// create a socket
			Socket socket = new Socket(hostname, port);
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			System.out.println("Welcome to Math Server-Client Code");
			System.out.println("1. Add two nos");
			System.out.println("2. Calculate factorial");
			System.out.println("3. Convert to binary");
			System.out.println("Your choice: ");
			Scanner s=new Scanner(System.in);
			int choice=s.nextInt();
			switch(choice)
			{
			case 1: 
				System.out.println("Enter two nos: ");
				int a=s.nextInt();
				int b=s.nextInt();
				writer.write(choice+":"+a+":"+b);
				break;
			case 2:
				System.out.println("Enter the number for whom you want to calculate the factorial for:");
				int n=s.nextInt();
				writer.write(choice+":"+n);
				break;
			case 3:
				System.out.println("Enter the number for which you want the binary");
				int k=s.nextInt();
				writer.write(choice+":"+k);
				break;
			default:
				System.out.println("Wrong Choice! Exiting!!");
				System.exit(0);
			}
			
			writer.newLine();
			writer.flush();
			// get the result from the server
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Result is "+reader.readLine());
			s.close();
			reader.close();
			writer.close();
			socket.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}

