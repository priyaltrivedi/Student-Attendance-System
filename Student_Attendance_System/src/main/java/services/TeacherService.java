package services;

import java.util.ArrayList;
import java.util.Iterator;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import Adapter.AttendanceAdapter;
import Adapter.StudentAdapter;
import attendance.Attendance;
import model.StudentDatabase;
import model.TeacherDatabase;
import student.StudentDetails;

public class TeacherService {
	
	public static void addStudent(StudentDetails std)
	{
	    Document stdDoc=StudentAdapter.toDocument(std);
	    TeacherDatabase.addStudent(stdDoc);	    
	}
	
	
	public static boolean teacherLogin(String username, String password)
	{
		return TeacherDatabase.teacherLogin(username, password);
	}
	
	public static boolean delete(String rollno)
	{
		return TeacherDatabase.deleteByRoll(rollno);
	}
	
	public static StudentDetails getByRoll(String rollno)
	{
		return TeacherDatabase.getByRoll(rollno);
	}
	
	
	public static boolean update(String rollno,StudentDetails std)
	{
		return TeacherDatabase.updateByRoll(rollno, std);
	}
	
	
	 public static ArrayList getAllStudents()
	{
		 ArrayList al=TeacherDatabase.retreiveAllStudents();
		return al;
	}
	 
	 public static void addAttendance(Attendance atd)
	{
		 Document stdDoc=AttendanceAdapter.toDocument(atd);
		 TeacherDatabase.addAttendance(stdDoc);	    
	}
	 

	 public static ArrayList shortAttendance()
	 {
		 ArrayList al=TeacherDatabase.shortAttendance();
		 return al;
	 }
	 
	 public static ArrayList viewFine()
	 {
		 ArrayList al=TeacherDatabase.viewFine();
		 return al;
	 }
	

}
