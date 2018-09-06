package services;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.bson.Document;

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
	  
	  public static ArrayList monthsWithLeaves(String rollno)
	  {
		 return StudentDatabase.monthsWithLeaves(rollno);
	  }
	  
	  public static Document monthWithMaxLeaves(String rollno)
	  {
		 return StudentDatabase.monthWithMaxLeaves(rollno);
	  }
	  
	  public static int currentMonthFine(String rollno,String month)
	  {
		  return StudentDatabase.currentMonthFine(rollno, month);
	  }
	
	  public static Document viewAttendance(String month,String rollno)
	  {
		  return StudentDatabase.viewAttendance(month,rollno);
	  }
}
