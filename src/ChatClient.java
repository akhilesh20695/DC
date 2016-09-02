import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient implements Runnable {

	private static Socket socket = null;
	private static BufferedWriter writer = null;
	private static BufferedReader reader = null;
	private static Scanner input = null;
	private static boolean closed = false;

	public static void main(String[] args) {

		int portNumber = 20000;
		String host = "localhost";


		System.out.println("Starting Chat Client..!!");
		try {
			socket = new Socket(host, portNumber);
			System.out.println("Chat Client started..!!");
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 

		try {

			new Thread(new ChatClient()).start();

			while (!closed) 
			{
				input = new Scanner(System.in);
				writer.write(input.nextLine().trim());
				writer.newLine();
				writer.flush();
			}

			writer.close();
			reader.close();
			socket.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void run() 
	{
		String response;
		try {
			while ((response = reader.readLine()) != null) {
				System.out.println(response);
				if (response.indexOf("Bye") != -1)
					break;
			}
			closed = true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}