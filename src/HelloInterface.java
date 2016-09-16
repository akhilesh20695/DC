import java.rmi.Remote;

public interface HelloInterface extends Remote{
	public String SayHello(String name) throws java.rmi.RemoteException;
}

