import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SqrtImpl extends UnicastRemoteObject implements SqrtInterface{
	public SqrtImpl() throws RemoteException
	{
		super();
	}
	
	public double[] Sqrt(double arr[]) throws RemoteException
	{
		int lenght=arr.length;
		for(int i=0;i<lenght;i++)
		{
			arr[i]=Math.sqrt(arr[i]);
		}
		return arr;
	}

}
