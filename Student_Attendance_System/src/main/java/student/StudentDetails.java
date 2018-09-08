package student;

import java.util.Date;

public class StudentDetails {
	
	private String username;
    private String password;
	private String name;
	private String gender;
	private String department;
	private String mobileno;
	private String address;
	private String motherName;
	private String fatherName;
	private String semester;
	private String emailId;
	private String dob;
	private String rollno;
	
	public StudentDetails() {
		// TODO Auto-generated constructor stub
	}

	public StudentDetails(String username, String password, String name, String gender, String rollno,String department, String mobileno,String address,
			String motherName, String fatherName, String semester, String emailId, String dob) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender=gender;
		this.rollno= rollno;
		this.department = department;
		this.mobileno = mobileno;
		this.address=address;
		this.motherName = motherName;
		this.fatherName = fatherName;
		this.semester = semester;
		this.emailId = emailId;
		this.dob = dob;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	
	
	
	
	
	
	
	

}
