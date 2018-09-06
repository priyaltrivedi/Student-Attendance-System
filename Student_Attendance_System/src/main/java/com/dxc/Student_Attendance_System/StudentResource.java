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
import services.TeacherService;
import student.StudentDetails;

@Path("student")
public class StudentResource implements Serializable {
	
	@POST
    @Path("login/{uname}/{password}")
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
	 @Path("viewProfile/{rollno}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getdetails (@PathParam("rollno") String rollno)
	 {
		 return Response.ok(StudentService.getDetails(rollno)).build();
	
	 }
	 
	 @GET
	 @Path("monthsWithLeaves/{rollno}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response monthsWithLeaves (@PathParam("rollno") String rollno)
	 {

	    	return Response.ok(StudentService.monthsWithLeaves(rollno)).build();
	 }
	 
	 @GET
	 @Path("monthsWithMaxLeaves/{rollno}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response monthsWithMaxLeaves (@PathParam("rollno") String rollno)
	 {

	    	return Response.ok(StudentService.monthWithMaxLeaves(rollno)).build();
	 }
	 
	 
	 @GET
	 @Path("currentMonthFine/{rollno}/{month}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response monthsWithMaxLeaves (@PathParam("rollno") String rollno,@PathParam("month") String month)
	 {

	    	return Response.ok(StudentService.currentMonthFine(rollno, month)).build();
	 }
	 
	 @GET
	 @Path("viewAttendance/{month}/{rollno}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response viewAttendance (@PathParam("month") String month,@PathParam("rollno") String rollno)
	 {

	    	return Response.ok(StudentService.viewAttendance(month,rollno)).build();
	 }

}
