import java.io.*;
import java.rmi.*;

public class SqrtClient {
	
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
			String registryURL = "rmi://localhost:"+portNum+"/sqrt";
			System.out.println("Upto Here");
			SqrtInterface h = (SqrtInterface)Naming.lookup(registryURL);
//lookup to registry.list
			System.out.println("Lookup Completed\n ");
			double[] main_array=new double[100];
			for(int i=0;i<100;i++)
			{
				main_array[i]=i;
			}
			double copy[] = h.Sqrt(main_array);
			System.out.println("Modified Array is");
			for(int i=0;i<100;i++)
			{
				System.out.println(copy[i]);
			}
			
		
		} 
		
		catch(Exception e){
		
			System.out.println("Exception in HelloClient: : " +e);
					
		}
	}
}