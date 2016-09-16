import java.rmi.Remote;

public interface SqrtInterface extends Remote {
	public double[] Sqrt(double arr[]) throws java.rmi.RemoteException;

}
