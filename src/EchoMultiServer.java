import java.net.*;
import java.io.*;

class ChatServerThread extends Thread
{  
	private Socket         		socket   = null;	
	private BufferedReader 		reader =  null;
	private BufferedWriter 		writer=null;

	public ChatServerThread(Socket clientsocket) throws IOException
	{  
		socket = clientsocket;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	public void run()
	{  
		System.out.println("Clients running..!!");
		int exit=0;
		while (exit!=1)
		{  
			try
			{  
				String message=reader.readLine();
				if(message.equals(".bye"))
				{
					System.out.println("Client Disconnected..!!");
					close();
					socket.close();
					exit=1;
				}
				else
				{
					System.out.println(message);
					String reverse = new StringBuffer(message).reverse().toString();
					writer.write(reverse);
					writer.newLine();
					writer.flush();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close() throws IOException
	{  
		if (socket != null)    
			socket.close();
		
		if (reader != null)  
			reader.close();
		
		if(writer !=null) 
			writer.close();
	}
}


public class EchoMultiServer implements Runnable
{  
	private ServerSocket     server = null;
	private Thread           thread = null;

	public EchoMultiServer(int port)
	{  
		try
		{  
			System.out.println("Server starting..!!");
			server = new ServerSocket(port);  
			System.out.println("Server started..!!");
			start();
		}
		catch(Exception e)
		{  
			e.printStackTrace(); 
		}
	}
	
	public void run()
	{  
		while (thread != null)
		{  
			try
			{  
				System.out.println("Waiting for a client ..."); 
				addThread(server.accept());
			}
			catch(Exception e)
			{  
				e.printStackTrace();
			}
		}
	}
	public void addThread(Socket socket)
	{  
		System.out.println("New Client accepted..!!");
		try
		{  
			(thread=new ChatServerThread(socket)).start();
		}
		catch(Exception e)
		{  
			e.printStackTrace();
		}
	}
	
	public void start()                   
	{
		System.out.println("In the start function");
		if (thread == null)
		{  
			(thread=new Thread(this)).start();
		}
	}
	
	public void stop()                    
	{ 
		if (thread != null)
		{  
			thread = null;
		} 
	}
	public static void main(String args[])
	{
		int port=10000;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		EchoMultiServer server = new EchoMultiServer(port);	
	}
}

