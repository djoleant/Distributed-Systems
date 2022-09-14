import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.io.* ;

public class FacultyStudentClient
{
 
 private IFacultyManager FM;
 private IStudentCallback cb;
 
 public FacultyStudentClient(String host, String port, String server)
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
		cb=new StudentCallback();
	
		Input input = new Input(); 

		String index = input.getUserInput("Enter index number: ");
		String name = input.getUserInput("Enter first and last name: ");
		String email = input.getUserInput("Enter email: ");
		
		Student stud = new Student(index, name, email);
		String examId = input.getUserInput("Enter exam id: ");
		
		IExam exam = FM.FindExam(examId);
		exam.Register(stud,cb);
		
		int sCount = exam.GetRegisteredStudentCount();
		System.out.println("Registerd Student Count: " + sCount);

	} catch (RemoteException remoteException) {		
		System.err.println(remoteException);		
	}
 }
 
 public static void main(String[] args) {

	String host = args[0];
	String port = args[1];
	String server = args[2];
	
	new FacultyStudentClient(host, port, server);
 }
 
 /* CALLBACK IMPLEMENTATION */
 
 public class StudentCallback extends UnicastRemoteObject implements IStudentCallback {
	
	public StudentCallback() throws RemoteException 
	{		
	}
	
	public void notify(String msg) throws RemoteException 
	{
		System.out.println("New Message: "+msg);					
	}	
 }
 
 
}