import java.io.*;
import java.rmi.*;

public class HelloClient {
	
	public static void main(String args[ ]){
		try{
			int RMIPort;
			String hostName;
			InputStreamReader is = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
			System.out.println("Enter RMIRegistry host name: ");
//Enter localhost name
			hostName = br.readLine();
			System.out.println("Enter the RMIregistry port number: ");
//Enter Registry port number
			String portNum = br.readLine();
			RMIPort = Integer.parseInt(portNum);
			String registryURL = "rmi://localhost:"+portNum+"/hello";
			System.out.println("Upto Here");
			HelloInterface h = (HelloInterface)Naming.lookup(registryURL);
//lookup to registry.list
			System.out.println("Lookup Completed\n ");
			String message = h.SayHello("Akhilesh\n");
			System.out.println("Hello Client: "+ message);//print message
			
		
		} 
		
		catch(Exception e){
		
			System.out.println("Exception in HelloClient: : " +e);
					
		}
	}
}