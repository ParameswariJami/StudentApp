package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Course;

@Service
public class CourseDao { 

	@Autowired
	CourseRepository courseRepo;

	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}
	
	public Course getCourseById(int courseId) {
		return courseRepo.findById(courseId).orElse(null);
	}

	public Course addCourse(Course course) {
		return courseRepo.save(course);
	}

	public Course updateCourse(Course course) {
		return courseRepo.save(course);
	}

	public void deleteCourseById(int courseId) {
		courseRepo.deleteById(courseId);
	}
	
}
