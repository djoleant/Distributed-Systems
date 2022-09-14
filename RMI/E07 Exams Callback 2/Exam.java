import java.rmi.RemoteException;
import java.util.*;

public class Exam extends java.rmi.server.UnicastRemoteObject implements IExam
{

private String examId;
private String name;
private int studentCount;
private Map<String, Student> registeredStudents;
private Map<String, IStudentCallback> callbacks;

public Exam(String examId, String name) throws RemoteException
{ 
	this.examId = examId;
	this.name = name;
	this.studentCount = 0;
	registeredStudents = new HashMap<String,Student>();	
	callbacks = new HashMap<String,IStudentCallback>();	
}

public String GetExamId() throws RemoteException
{
	return this.examId;
}

public String GetName() throws RemoteException
{
	return this.name;
}

public int GetRegisteredStudentCount() throws RemoteException
{
	return this.studentCount;
}

public void Register(Student stud,IStudentCallback cb) throws RemoteException
{
	this.registeredStudents.put(stud.GetIndex(),stud);
	this.callbacks.put(stud.GetIndex(),cb);
	this.studentCount++;
}

public void NotifyStudents(String msg) throws RemoteException
{
	for (Map.Entry<String, IStudentCallback> entry : callbacks.entrySet()) {
		String Id = entry.getKey();
		IStudentCallback cb = (IStudentCallback)entry.getValue();			
		cb.notify(msg);		
	}
}

}



	
