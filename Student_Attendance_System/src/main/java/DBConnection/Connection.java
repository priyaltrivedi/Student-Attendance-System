package DBConnection;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connection {
	
	private static final String DBNAME="attendance";
	private static final String STUDENT_LOGIN="studentLogin";
	private static final String TEACHER_LOGIN="teacherLogin";
	private static final String STUDENT_COLLECTION="studentDetails";
	private static final String ATTENDANCE="attendance";
	
	
	private static MongoCollection<Document> tLogin; 
	private static MongoCollection<Document> sLogin; 
	private static MongoCollection<Document> sDetails; 
	private static MongoCollection<Document> attendance; 

	
	static
	{
		MongoClient mongo=new MongoClient("localhost",27017);
		MongoDatabase database=mongo.getDatabase(DBNAME);
		
		sLogin=database.getCollection(STUDENT_LOGIN);
		sDetails=database.getCollection(STUDENT_COLLECTION);
		tLogin=database.getCollection(TEACHER_LOGIN);
		sDetails=database.getCollection(STUDENT_COLLECTION);
		attendance = database.getCollection(ATTENDANCE);
		
	}
	
	public static MongoCollection<Document> getStudentLoginCollection()
	{
		return sLogin;
	}
	
	public static MongoCollection<Document> getTeacherLoginCollection()
	{
		return tLogin;
	}
	
	public static MongoCollection<Document> getStudentCollection()
	{
		return sDetails;
	}

	public static MongoCollection<Document> getAttendanceCollection()
	{
		return attendance;
	}


}
