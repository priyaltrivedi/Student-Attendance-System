package model;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import Adapter.StudentAdapter;
import DBConnection.Connection;
import attendance.Attendance;
import student.StudentDetails;

public class TeacherDatabase {
	
	static MongoCollection<Document> teacherLogin;
	static MongoCollection<Document> studentDetails;
	static MongoCollection<Document> attendance;
	
	static {
		teacherLogin=Connection.getStudentLoginCollection();
		studentDetails=Connection.getStudentCollection();
		attendance=Connection.getAttendanceCollection();
	}
	
	public static boolean teacherLogin(String uname,String passwd)
	{
		FindIterable<Document> fitr=teacherLogin.find();
		Boolean flag=null;
		 Iterator it=fitr.iterator();
		  while(it.hasNext())
		  {
			  Document d=(Document)it.next();
			 String unm = d.getString("uname");
			 String pass = d.getString("passwd");
			 if(unm.equals(uname) && pass.equals(passwd))
			 {
				 System.out.println(unm+""+passwd);
				 flag=true;
			 }
			 else
				 flag=false;
		  }
		  return flag;
	}
	
	public static ArrayList retreiveAllStudents()
	{
		ArrayList al=new ArrayList();
		FindIterable<Document>fit=studentDetails.find();
		Iterator<Document> it=fit.iterator();
		while(it.hasNext())
		{
		 Document Doc=(Document)it.next();	
		StudentDetails details=StudentAdapter.toObject(Doc);
		 al.add(details);
		}
		return al;
	}
	
	
	public static void addStudent(Document std)
	{
		studentDetails.insertOne(std);	
	}
	
	
	public static boolean deleteByRoll(String rollno)
	{
		Bson filter = new Document("rollno", rollno);
		DeleteResult doc=studentDetails.deleteOne(filter);
		return doc.wasAcknowledged();
	}
	
	public static StudentDetails getByRoll(String rollno)
	{
		FindIterable<Document> fitr=studentDetails.find(Filters.eq("rollno",rollno));
		StudentDetails details=null;
		Iterator it = fitr.iterator();
		{
			 Document Doc=(Document)it.next();	
			details=StudentAdapter.toObject(Doc);
		}
		return details;
	}
	
	
	
	public static boolean updateByRoll(String rollno,StudentDetails std)
	{
		
		Bson filter = new Document("rollno", rollno);
		Bson newValue = new Document("mobileno",std.getMobileno())
				.append("address", std.getAddress())
				.append("semester", std.getSemester())
				.append("emailId", std.getEmailId());
		Bson updateOperationDocument = new Document("$set", newValue);
		UpdateResult update= studentDetails.updateMany(filter, updateOperationDocument);
		return update.wasAcknowledged();
	}
	
	public static void addAttendance(Document atd)
	{
		attendance.insertOne(atd);
	}

	public static void shortAttendance()
	{
		int percent;
		int attend;
		int total;
		ArrayList al1=new ArrayList();
		ArrayList al2=new ArrayList();
		al1.add(Aggregates.group("$date", Accumulators.sum("total", 1)));
		Iterator it1=attendance.aggregate(al1).iterator();
		while(it1.hasNext())
		{
			al2.add(it1.next());
		}
		total=al2.size();
		System.out.println(total);
		ArrayList al3=new ArrayList();
		ArrayList al4=new ArrayList();
		
		al3.add(Aggregates.match(Filters.eq("status", "true")));
   		al3.add(Aggregates.group("$roll", Accumulators.sum("count", 1)));
		Iterator it2=attendance.aggregate(al3).iterator();
		while(it2.hasNext())
		{
			al4.add(it2.next());
		}
		ArrayList al5=new ArrayList();
		Iterator it3=al4.iterator();
		while(it3.hasNext())
		{
			Document doc=(Document)it3.next();
			String roll=doc.getString("roll");
			//double total=doc.getDouble("count");
		}
//		 attend=al4.size();
//		 System.out.println(attend);
//	     percent = (attend/total)*100;
	     
		}
			  
	}
