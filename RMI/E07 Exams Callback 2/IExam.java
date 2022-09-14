import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IExam extends Remote
{
	public String GetExamId() throws RemoteException;
	
	public String GetName() throws RemoteException;
	
	public int GetRegisteredStudentCount() throws RemoteException;
	
	public void Register(Student stud,IStudentCallback cb) throws RemoteException;
	
	public void NotifyStudents(String msg) throws RemoteException;
	
}