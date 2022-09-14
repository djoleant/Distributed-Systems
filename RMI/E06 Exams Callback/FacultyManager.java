import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class FacultyManager extends UnicastRemoteObject implements IFacultyManager {

private Map<String, IExam> exams;

public FacultyManager() throws java.rmi.RemoteException
{
   exams = new HashMap<String, IExam>();
   IExam exam1 = new Exam("rm","Racunarske mreze");
   IExam exam2 = new Exam("ds","Distribuirani sistemi");
 
   exams.put("rm", exam1);
   exams.put("ds", exam2); 
}

public IExam FindExam(String examId) throws RemoteException
{
	IExam exam = (IExam)this.exams.get(examId);
	
	return exam;
}

}
