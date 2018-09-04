package student;

public class StudentLogin {
	
	private String uname;
    private String passwd;
    
    public StudentLogin() {
		// TODO Auto-generated constructor stub
	}
    
    
	public StudentLogin(String uname, String passwd) {
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
