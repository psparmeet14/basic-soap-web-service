package com.pslearning.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.pslearning.courses.CourseDetails;
import com.pslearning.courses.DeleteCourseDetailsRequest;
import com.pslearning.courses.DeleteCourseDetailsResponse;
import com.pslearning.courses.GetAllCourseDetailsRequest;
import com.pslearning.courses.GetAllCourseDetailsResponse;
import com.pslearning.courses.GetCourseDetailsRequest;
import com.pslearning.courses.GetCourseDetailsResponse;
import com.pslearning.courses.Status;
import com.pslearning.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.pslearning.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.pslearning.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.pslearning.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.StatusBean;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	private CourseDetailsService service;
	
	@PayloadRoot(namespace="http://pslearning.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		
		if (course == null)
			throw new CourseNotFoundException("Invalid course ID " + request.getId());
		
		return mapCourseDetails(course);
	}
	
	@PayloadRoot(namespace="http://pslearning.com/courses", localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses = service.findAll();
		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace="http://pslearning.com/courses", localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		StatusBean status = service.deleteById(request.getId());
		response.setStatus(mapStatus(status));
		return response;
	}

	private Status mapStatus(StatusBean status) {
		if (status == StatusBean.FAILURE)
			return Status.FAILURE;
		return Status.SUCCESS;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for(Course course: courses) {
			response.getCourseDetails().add(mapCourse(course));
		}
		return response;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

	
}
