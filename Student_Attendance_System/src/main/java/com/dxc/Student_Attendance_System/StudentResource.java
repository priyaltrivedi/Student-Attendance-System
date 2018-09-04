package com.dxc.Student_Attendance_System;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import services.StudentService;
import student.StudentDetails;

@Path("student")
public class StudentResource implements Serializable {
	
	@POST
    @Path("student/login/{uname}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response studentLogin(@PathParam("uname") String uname, @PathParam("password") String pass)
    {
   
      if( StudentService.studentLogin(uname,pass))
    	  return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}").build();
      
	  else 
		  return Response.ok("{\"status\":\"1\",\"message\":\"User not found\"}").build(); 
   }
	
	 @GET
	 @Path("student/viewProfile/{rollno}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public StudentDetails getdetails (@PathParam("rollno") String rollno)
	 {
		StudentDetails std= StudentService.getDetails(rollno);
		return std;
	 }
	 
	


}
