package model;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import Adapter.StudentAdapter;
import DBConnection.Connection;
import student.StudentDetails;

public class StudentDatabase {
	
	static MongoCollection<Document> studentLogin;
	static MongoCollection<Document> studentDetails;
	
	static {
		studentLogin=Connection.getStudentLoginCollection();
		studentDetails=Connection.getStudentCollection();
	}
	
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

}
