import java.io.Serializable;

public class Student implements Serializable {

private String index;
private String name;
private String email;

public Student(String index, String name, String email)
{
	this.index = index;
	this.name = name;
	this.email = email;
}

public String GetIndex()
{
	return this.index;
}

public String GetName()
{
	return this.name;
}

public String GetEmail()
{
	return this.email;
}

}