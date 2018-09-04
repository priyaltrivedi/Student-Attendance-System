package Adapter;

import org.bson.Document;

import student.StudentDetails;



public class StudentAdapter {
	
	public static StudentDetails toObject(Document d)
	{
		StudentDetails std=new StudentDetails();
		std.setUsername(d.getString("username"));
		std.setPassword(d.getString("password"));
		std.setName(d.getString("name"));
		std.setGender(d.getString("gender"));
		std.setRollno(d.getString("rollno"));
		std.setDepartment(d.getString("department"));
		std.setFatherName(d.getString("fatherName"));
		std.setMotherName(d.getString("motherName"));
		std.setMobileno(d.getString("mobileno"));
		std.setAddress(d.getString("address"));
		std.setSemester(d.getString("semester"));
		std.setDob(d.getString("dob"));
		std.setEmailId(d.getString("emailId"));
		

		return std;
		
	}
	
	public static Document toDocument(StudentDetails std)
	{
		Document doc=new Document()
				.append("username", std.getUsername())
				.append("password", std.getPassword())
				.append("name",std.getName())
				.append("gender", std.getGender())
				.append("rollno", std.getRollno())
				.append("department", std.getDepartment())
				.append("fatherName", std.getFatherName())
				.append("motherName", std.getMotherName())
				.append("mobileno", std.getMobileno())
				.append("address", std.getAddress())
				.append("semester", std.getSemester())
				.append("dob", std.getDob())
				.append("emailId", std.getEmailId());
		return doc;

	}
	

}
