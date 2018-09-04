package Adapter;

import org.bson.Document;

import student.StudentLogin;


public class StudentLoginAdapter {
	
	public static StudentLogin toObject(Document d)
	{
		StudentLogin sLogin=new StudentLogin();
		sLogin.setUname(d.getString("uname"));
		sLogin.setPasswd(d.getString("passwd"));
		
		return sLogin;
		
	}
		
	
	

}
