package services;

import java.util.ArrayList;
import model.StudentDatabase;
import model.TeacherDatabase;
import student.StudentDetails;

public class StudentService {
	
	public static boolean studentLogin(String username, String password)
	  {
		return StudentDatabase.studentLogin(username, password);
	  }
	  
	  public static StudentDetails getDetails(String roll)
	  {
		  StudentDetails std=StudentDatabase.getProfile(roll);
		  return std;
	  }
	  
	

}
