import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MathServer {

	public static int add(int a,int b)
	{
		return a+b;
	}
	public static int factorial(int a)
	{
		int result=1;
		while(a>0)
		{
			result=result*a;
			a--;
		}
		return result;
	}

	

	public static String calculate(String line) throws IllegalArgumentException {

		String [] elements = line.split(":");
		if (elements.length != 3 && elements.length!=2)
			throw new IllegalArgumentException("parsing error!");
		int choice=Integer.parseInt(elements[0]);
		String Result=new String();
		switch(choice)
		{
		case 1:
			int a=Integer.parseInt(elements[1]);
			int b=Integer.parseInt(elements[2]);
			int sum=add(a,b);
			Result=Integer.toString(sum);
			break;
		case 2:
			int n=Integer.parseInt(elements[1]);
			int fact=factorial(n);
			Result=Integer.toString(fact);
			break;
		case 3:
			int k=Integer.parseInt(elements[1]);
			Result=Integer.toBinaryString(k);
		}
		return Result;

	}


	public static void main(String [] args)throws Exception
	{
		int port = 10000;
		System.out.println("Math Server is running...");
		// create a server socket and wait for client’s connection
		ServerSocket serverSocket = new ServerSocket(port);
		Socket socket = serverSocket.accept();
		System.out.println("Client Connected..Waiting for client input");
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			// read the message from client and parse the execution
			String line = reader.readLine();
			System.out.println("Client Inputs received...calculating..!!");
			String result = calculate(line);
			// write the result back to the client
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			writer.write(""+result);
			writer.newLine();
			writer.flush();
			// close the stream
			reader.close();
			writer.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		serverSocket.close();
		System.out.println("Math Server is closed...");
	}

}
