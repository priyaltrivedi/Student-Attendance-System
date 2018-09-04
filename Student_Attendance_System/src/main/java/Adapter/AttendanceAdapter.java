package Adapter;

import org.bson.Document;
import java.text.SimpleDateFormat;
import attendance.Attendance;
import student.StudentDetails;

public class AttendanceAdapter {

	
	public static Attendance toObject(Document d)
	{
		Attendance atd=new Attendance();
		atd.setRollno(d.getString("rollno"));
		atd.setName(d.getString("name"));
		atd.setDate(d.getString("date"));
		atd.setMonth(d.getString("month"));
		atd.setYear(d.getString("year"));
		atd.setStatus(d.getString("status"));
		return atd;
		
	}
	
	public static Document toDocument(Attendance atd)
	{
		Document doc=new Document()
				.append("rollno", atd.getRollno())
				.append("name", atd.getName())
				.append("date", atd.getDate())
				.append("month", atd.getMonth())
				.append("year", atd.getYear())
				.append("status", atd.getStatus());
		return doc;

	}
	
}
