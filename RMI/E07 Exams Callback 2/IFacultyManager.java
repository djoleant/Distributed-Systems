import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFacultyManager extends Remote
{
	
	public IExam FindExam(String examId) throws RemoteException;
	
}