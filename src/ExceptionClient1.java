import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ExceptionClient1 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		String hostname="localhost";
		int port=10000;
		Socket socket=new Socket(hostname,port);
		System.out.println("Connected to server..Waiting for server to respond");
		socket.setSoTimeout(5000);
		System.out.println("Will wait for 5 seconds before timeout..");
		BufferedReader reader=null;
		InputStream is=socket.getInputStream();
		int i;
		try{
			//reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			i=is.read();
		}
		catch(SocketTimeoutException e)
		{
			System.out.println("Server is not responding..!! Exitting the program..!!");
			System.exit(0);
		}
		String result=reader.readLine();
		System.out.println(result);
		socket.close();
	}

}
