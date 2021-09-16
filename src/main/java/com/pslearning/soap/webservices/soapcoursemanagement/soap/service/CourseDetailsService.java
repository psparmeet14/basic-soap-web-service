package com.pslearning.soap.webservices.soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pslearning.soap.webservices.soapcoursemanagement.soap.bean.Course;

@Service
public class CourseDetailsService {
	
	public enum StatusBean {
		SUCCESS, FAILURE
	}

	private static List<Course> courses = new ArrayList<>();
	
	static {
		Course course1 = new Course(1, "Spring Framework", "Complete spring framework master course");
		courses.add(course1);
		
		Course course2 = new Course(2, "Microservies", "This is hands-on microservices course");
		courses.add(course2);
		
		Course course3 = new Course(3, "Data Structures & Algorithms", "Java DSA complete guide");
		courses.add(course3);
		
		Course course4 = new Course(4, "Angular", "Front end angular complete boot camp");
		courses.add(course4);
	}
	
	public Course findById(int id) {
		for (Course course : courses) {
			if (course.getId() == id) {
				return course;
			}
		}
		return null;
	}
	
	public List<Course> findAll() {
		return courses;
	}
	
	public StatusBean deleteById(int id) {
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return StatusBean.SUCCESS;
			}
		}
		return StatusBean.FAILURE;
	}
}
