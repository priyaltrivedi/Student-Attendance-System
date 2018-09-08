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
		
		if(doc.getDeletedCount()==1)
			return true;
		else
			return false;
		
	}
	
	public static StudentDetails getByRoll(String rollno)
	{
		FindIterable<Document> fitr=studentDetails.find(Filters.eq("rollno",rollno));
		StudentDetails details=null;
		Iterator it = fitr.iterator();
		Document Doc=(Document)it.next();	
		details=StudentAdapter.toObject(Doc);
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

	public static ArrayList shortAttendance()
	{
		double percent;
		int attend;
		int total;
		String name;
		ArrayList al1=new ArrayList();
		ArrayList al2=new ArrayList();
		al1.add(Aggregates.group("$date", Accumulators.sum("total", 1)));
		Iterator it1=attendance.aggregate(al1).iterator();
		while(it1.hasNext())
		{
			al2.add(it1.next());
		}
		total=al2.size();
		System.out.println("Total:"+total);
		ArrayList al3=new ArrayList();
		ArrayList al4=new ArrayList();
		Document d=new Document();
		d.append("_id", 1);
		al3.add(Aggregates.match(Filters.eq("status", "true")));
   		al3.add(Aggregates.group("$rollno", Accumulators.sum("count", 1)));
   		al3.add(Aggregates.sort(d));
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
			String roll=doc.getString("_id");
			int count=doc.getInteger("count");
			System.out.println();
			System.out.println("Roll no. "+roll);
			System.out.println("Attendance: "+count);
			percent=(count*100)/total;
			System.out.println("Percentage: "+percent);
			if(percent<=70)
			{
				FindIterable<Document> fitr2=attendance.find(Filters.eq("rollno",roll));
				Iterator it4 = fitr2.iterator();
			
					Document nameDoc=(Document)it4.next();
					name=nameDoc.getString("name");
					System.out.println(it4.next());
					Document result=new Document();
				result.append("rollno", roll)
					  .append("name", name)
					  .append("percent", percent);
				al5.add(result);
			}
		}
		return al5;
	}
	
	public static ArrayList viewFine()
	{
		ArrayList al1 = new ArrayList();
		ArrayList al2 = new ArrayList();
		int fine;
		String name;
		Document d=new Document();
		d.append("_id", 1);
		al1.add(Aggregates.match(Filters.eq("status", "false")));
   		al1.add(Aggregates.group("$rollno", Accumulators.sum("count", 1)));
   		al1.add(Aggregates.sort(d));
   		Iterator it1=attendance.aggregate(al1).iterator();
		while(it1.hasNext())
		{
			Document doc=(Document)it1.next();
			String roll=doc.getString("_id");
			int absent=doc.getInteger("count");
			fine=(absent*10);
			
			FindIterable<Document> fitr=attendance.find(Filters.eq("rollno",roll));
			Iterator it2 = fitr.iterator();
			Document nameDoc=(Document)it2.next();
				name=nameDoc.getString("name");
				System.out.println(it2.next());
			Document result=new Document();
			result.append("rollno", roll)
				  .append("name", name)
				  .append("fine", fine);
			al2.add(result);
		}
		return al2;
	}
			  
}
