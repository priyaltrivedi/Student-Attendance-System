package model;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import Adapter.StudentAdapter;
import DBConnection.Connection;
import student.StudentDetails;

public class StudentDatabase {
	
	static MongoCollection<Document> studentLogin;
	static MongoCollection<Document> studentDetails;
	static MongoCollection<Document> attendance;
	
	static {
		studentLogin=Connection.getStudentLoginCollection();
		studentDetails=Connection.getStudentCollection();
		attendance=Connection.getAttendanceCollection();
	}
	
	//Matching of Student login details
	public static boolean studentLogin(String uname,String passwd)
	{
		FindIterable<Document> fitr=studentLogin.find();
		 Boolean flag=null;
		  Iterator<Document> it=fitr.iterator();
		  while(it.hasNext())
		  {
			  Document d= it.next();
			 String unm = d.getString("uname");
			 String pass = d.getString("passwd");
			 if(unm.equals(uname) && pass.equals(passwd))
			 {
				 flag=true;
				 System.out.println(uname+""+passwd);
			 }
			 else
				 flag=false;
  		  }
		  return flag;
	}
	
	
	//Get Profile of a student based on roll no 
	public static StudentDetails getProfile(String roll)
	{
		FindIterable<Document> fitr=studentDetails.find(Filters.eq("rollno",roll));
		StudentDetails std=null;
		Iterator it = fitr.iterator();
		while(it.hasNext())
		{
			Document doc=(Document)it.next();
			std=StudentAdapter.toObject(doc);
		}
		System.out.println(std);
		return std;

	}
	
	//Get list of leaves of all months based on student's roll no 
	public static ArrayList monthsWithLeaves(String rollno)
	{
		ArrayList al1=new ArrayList();
		ArrayList al2=new ArrayList();
		al1.add(Aggregates.match(Filters.eq("rollno", rollno)));
		al1.add(Aggregates.match(Filters.eq("status", "false")));
   		al1.add(Aggregates.group("$month", Accumulators.sum("leave", 1)));
   		Iterator it=attendance.aggregate(al1).iterator();
		while(it.hasNext())
		{
			Document doc=(Document)it.next();
			al2.add(doc);
		}
		return al2;
	}
	
	//Get month with maximum leaves taken 
	public static Document monthWithMaxLeaves(String rollno)
	{
		ArrayList al1=new ArrayList();
		Document d=new Document();
		d.append("leave", -1);
		al1.add(Aggregates.match(Filters.eq("rollno", rollno)));
		al1.add(Aggregates.match(Filters.eq("status", "false")));
   		al1.add(Aggregates.group("$month", Accumulators.sum("leave", 1)));
   		al1.add(Aggregates.sort(d));
   		al1.add(Aggregates.limit(1));
   		Iterator it=attendance.aggregate(al1).iterator();
		Document doc=(Document)it.next();
		return doc;
	}
	
	//view fine of current month
	public static int currentMonthFine(String rollno,String month)
	{
		ArrayList al1=new ArrayList();
		ArrayList al2=new ArrayList();
		int total;
		int fine;
		al1.add(Aggregates.match(Filters.eq("rollno", rollno)));
		al1.add(Aggregates.match(Filters.eq("status", "false")));
		al1.add(Aggregates.match(Filters.eq("month", month)));
		Iterator it1=attendance.aggregate(al1).iterator();
		while(it1.hasNext())
		{
			al2.add(it1.next());
		}
		total=al2.size();
		fine=total*10;
		return fine;
	}
	
	//view attendance on month basis
	public static Document viewAttendance(String month,String rollno)
	{
		
		int absent;
		int present;
		Document d=new Document();
		ArrayList al1=new ArrayList();
		ArrayList al2=new ArrayList();
		al1.add(Aggregates.match(Filters.eq("rollno", rollno)));
		al1.add(Aggregates.match(Filters.eq("status", "false")));
		al1.add(Aggregates.match(Filters.eq("month", month)));
		Iterator it1=attendance.aggregate(al1).iterator();
		while(it1.hasNext())
		{
			al2.add(it1.next());
		}
		absent=al2.size();
		
		ArrayList al3=new ArrayList();
		ArrayList al4=new ArrayList();
		al3.add(Aggregates.match(Filters.eq("rollno", rollno)));
		al3.add(Aggregates.match(Filters.eq("status", "true")));
		al3.add(Aggregates.match(Filters.eq("month", month)));
		Iterator it2=attendance.aggregate(al3).iterator();
		while(it2.hasNext())
		{
			al4.add(it2.next());
		}
		present=al4.size();
		d.append("present", present).append("absent", absent);
		return d;
	}
	
	

}
