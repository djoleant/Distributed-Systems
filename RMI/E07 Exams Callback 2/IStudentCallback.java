import java.rmi.*;

public interface IStudentCallback extends Remote {
	
	void notify(String msg) throws RemoteException;
	
}