package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.CourseDao;
import com.model.Course;

@RestController
public class CourseController {
	
	@Autowired
	CourseDao courseDao;
	
	@GetMapping("getAllCourses")
	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}
	
	@GetMapping("getCourseById/{id}")
	public Course getCourseById(@PathVariable("id") int courseId) {
		return courseDao.getCourseById(courseId);
	}
		
	@PostMapping("addCourse")
	public Course addCourse(@RequestBody Course course) {
		return courseDao.addCourse(course);
	}
	
	@PutMapping("updateCourse")
	public Course updateCourse(@RequestBody Course course) {
		return courseDao.updateCourse(course);
	}
	
	@DeleteMapping("deleteCourseById/{id}")
	public String deleteCourseById(@PathVariable("id") int courseId) {
		courseDao.deleteCourseById(courseId);
		return "Course Deleted Successfully!!";
	}
}

