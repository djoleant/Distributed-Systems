import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.io.* ;

public class FacultyAdminClient
{
 
 private IFacultyManager FM;
  
 public FacultyAdminClient(String host, String port, String server)
 {
	try
	{
		FM = (IFacultyManager)Naming.lookup("rmi://"+host + ":" + port + "/" + server);
	} catch (MalformedURLException malformedException) {
		System.err.println("Bad URL: " + malformedException);
	} catch (NotBoundException notBoundException)
	{
		System.err.println("Not Bound: " + notBoundException);
	} catch (RemoteException remoteException) {
		System.err.println("Remote Exception: " +remoteException);
	}
 
	try
	{		
		Input input = new Input(); 

		String examId = input.getUserInput("Enter Exam Id: ");
		String msg = input.getUserInput("Enter Message: ");
		
		IExam exam = FM.FindExam(examId);
		exam.NotifyStudents(msg);		
		
	} catch (RemoteException remoteException) {		
		System.err.println(remoteException);		
	}
 }
 
 public static void main(String[] args) {

	String host = args[0];
	String port = args[1];
	String server = args[2];
	
	new FacultyAdminClient(host, port, server);
 }
 
 
}