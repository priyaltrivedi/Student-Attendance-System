package teacher;

public class TeacherLogin {
	
	private String uname;
    private String passwd;


public TeacherLogin() {
	// TODO Auto-generated constructor stub
}
public TeacherLogin(String uname, String passwd) {
	super();
	this.uname = uname;
	this.passwd = passwd;
}

public String getUname() {
	return uname;
}

public void setUname(String uname) {
	this.uname = uname;
}

public String getPasswd() {
	return passwd;
}

public void setPasswd(String passwd) {
	this.passwd = passwd;
}

}
