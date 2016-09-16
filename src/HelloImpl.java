import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements HelloInterface {

	public HelloImpl() throws RemoteException
	{
		super();
	}
	
	public String SayHello(String name) throws RemoteException
	{
		return "Hello World! " + name;
	}
	
}

