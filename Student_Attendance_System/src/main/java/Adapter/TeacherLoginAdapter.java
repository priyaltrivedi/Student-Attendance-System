package Adapter;

import org.bson.Document;

import teacher.TeacherLogin;

public class TeacherLoginAdapter {

	
	public static TeacherLogin toObject(Document d)
	{
		TeacherLogin tLogin=new TeacherLogin();
		tLogin.setUname(d.getString("uname"));
		tLogin.setPasswd(d.getString("passwd"));
		
		return tLogin;
		
	}
}
