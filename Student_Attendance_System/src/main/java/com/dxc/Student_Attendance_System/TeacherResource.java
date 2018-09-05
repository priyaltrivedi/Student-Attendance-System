package com.dxc.Student_Attendance_System;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import attendance.Attendance;
import services.StudentService;
import services.TeacherService;
import student.StudentDetails;

@Path("teacher")
public class TeacherResource implements Serializable {

	@POST
    @Path("login/{uname}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response teacherLogin(@PathParam("uname") String uname, @PathParam("password") String pass)
    {
   
      if( TeacherService.teacherLogin(uname,pass))
    	return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}").build();
     
      else 
    	  return Response.ok("{\"status\":\"1\",\"message\":\"User not found\"}").build();  
    	

    }

 	

	@POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(StudentDetails input)
    {
 
    	TeacherService.addStudent(input);
    	return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}").build();
    }
	
	
	
		@DELETE
	    @Path("delete/{rollno}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response delete(@PathParam("rollno") String rollno)
	    {
	        if(TeacherService.delete(rollno))
	        	return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}").build();
	        else
	        	return Response.ok("{\"status\":\"1\",\"message\":\"Roll no. not found\"}").build();
	    }
	 
	 	@PUT
	    @Path("update/{rollno}")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response update (@PathParam("rollno") String rollno,StudentDetails input)
	    {
	    	if(TeacherService.update(rollno, input))
	    	return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}")
	    			.build();
        else
        	return Response.ok("{\"status\":\"1\",\"message\":\"Roll no. not found\"}").build();
	    }
	 	
	 	@GET
	 	@Path("getbyroll/{rollno}")
	 	@Produces(MediaType.APPLICATION_JSON)
	 	public Response getByRollno(@PathParam("rollno") String roll)
	 	{
	 		return Response.ok(TeacherService.getByRoll(roll)).build();
	 	}
	 	
	 	  
		 @GET
		 @Path("getAll")
		 @Produces(MediaType.APPLICATION_JSON)
		 public Response getAll() {
		    	
		    	return Response.ok(TeacherService.getAllStudents()).build();
		    	
		    }
		 
		 	@PUT
		    @Path("addAttendance")
		    @Produces(MediaType.APPLICATION_JSON)
		    @Consumes(MediaType.APPLICATION_JSON)
		    public Response update (Attendance input)
		    {
			 	TeacherService.addAttendance(input);
		    	return Response.ok("{\"status\":\"0\",\"message\":\"successfuly added\"}")
		    			.build();
		    }
		 	
		 	 @GET
			 @Path("shortAttendance")
			 @Produces(MediaType.APPLICATION_JSON)
			 public Response shortAttendance() {
			    	
			    	return Response.ok(TeacherService.shortAttendance()).build();
			    	
			    }
		 	 
		 	 @GET
			 @Path("viewFine")
			 @Produces(MediaType.APPLICATION_JSON)
			 public Response viewFine() {
			    	
			    	return Response.ok(TeacherService.viewFine()).build();
			    	
			    }
		 	
		 	
		 	
		 	
		 
		 
}
