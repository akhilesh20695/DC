import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.ServerSocket;


public class ChatServerSync {

	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static final int maxClientsCount = 10;
	private static final clientThreadSync[] threads = new clientThreadSync[maxClientsCount];

	public static void main(String args[]) {

		int portNumber = 20000; 

		try 
		{
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Chat Server Started..!!");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		while(true) 
		{
			try 
			{
				clientSocket = serverSocket.accept();
				System.out.println("New Client Connected..!!");
				int i = 0;
				for (i = 0; i < maxClientsCount; i++) 
				{
					if (threads[i] == null) 
					{
						(threads[i] = new clientThreadSync(clientSocket, threads)).start();
						break;
					}
				}

				if (i == maxClientsCount) 
				{
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
					writer.write("Server too busy. Try later.");
					writer.newLine();
					writer.flush();
					writer.close();
					clientSocket.close();
				}
			} 
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	}
}


class clientThreadSync extends Thread 
{

	private BufferedReader reader = null;
	private BufferedWriter writer = null;
	private Socket clientSocket = null;
	private final clientThreadSync[] threads;
	private int maxClientsCount;

	public clientThreadSync(Socket clientSocket, clientThreadSync[] threads) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		maxClientsCount = threads.length;
	}

	public void run() {
		int maxClientsCount = this.maxClientsCount;
		clientThreadSync[] threads = this.threads;

		try {
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			writer.write("Enter your name.");
			writer.newLine();
			String name = reader.readLine().trim();
			writer.write("Hey " + name
					+ "Welcome to our chat room.\n To leave enter .bye in a new line. \n Start Chatting..!! :)");
			writer.newLine();
			writer.flush();
			synchronized(this)
			{
				for (int i = 0; i < maxClientsCount; i++) 
				{
					if (threads[i] != null && threads[i] != this) 
					{
						threads[i].writer.write(name + " joined the chat room !!!");
						threads[i].writer.newLine();
						threads[i].writer.flush();
					}
				}
			}

			while (true) 
			{
				String line = reader.readLine();
				if (line.equals(".bye")) 
				{
					break;
				}
				synchronized(this)
				{
					for (int i = 0; i < maxClientsCount; i++) 
					{
						if (threads[i] != null) 
						{
							threads[i].writer.write("<" + name + ">" + line);
							threads[i].writer.newLine();
							threads[i].writer.flush();
						}
					}
				}
			}

			synchronized(this)
			{
				for (int i = 0; i < maxClientsCount; i++) 
				{
					if (threads[i] != null && threads[i] != this) 
					{
						threads[i].writer.write("The user " + name
								+ " left the chat room !!!");
						threads[i].writer.flush();
					}
				}
			}

			writer.write("Bye " + name + "! :(. Hope to see you again..!!");
			writer.newLine();
			writer.flush();
			
			synchronized(this)
			{
				for (int i = 0; i < maxClientsCount; i++) 
				{
					if (threads[i] == this) {
						threads[i] = null;
					}
				}
			}
			
			reader.close();
			writer.close();
			clientSocket.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}