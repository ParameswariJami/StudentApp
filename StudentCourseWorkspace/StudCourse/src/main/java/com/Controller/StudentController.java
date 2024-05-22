package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.StudentDao;
import com.model.Student;
import com.service.EmailService;
import com.service.OtpService;

import com.dao.CourseDao;
import com.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class StudentController {
	 private static final Logger logger = LoggerFactory.getLogger(StudentController.class);	
	
	 @Autowired
	    private StudentDao studDao;

	    @Autowired
	    private EmailService emailService;

	    @Autowired
	    private OtpService otpService;
	    
	    @Autowired
	    private CourseDao courseDao;
	    
	    
	
	@GetMapping("studLogin/{emailId}/{password}")
	public Student studLogin(@PathVariable("emailId") String emailId, 
			@PathVariable("password") String password) {
		
		return studDao.studLogin(emailId, password);
	}
	
	@GetMapping("getAllStudents")
	public List<Student> getAllStudents() {
		return studDao.getAllStudents();
	}
	
	@GetMapping("getStudentById/{id}")
	public Student getStudentById(@PathVariable("id") int studId) {
		return studDao.getStudentById(studId);
	}
	
	@GetMapping("getStudentByName/{name}")
	public List<Student> getStudentByName(@PathVariable("name") String studName) {
		return studDao.getStudentByName(studName);
	}
	
//	@PostMapping("addStudent")
//	public ResponseEntity<?> addStudent(@RequestBody Student stud) {
//	    try {
//	        Student newStudent = studDao.addStudent(stud); 
//	        return ResponseEntity.ok(newStudent); 
//	    } catch (DataIntegrityViolationException e) { 
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ID already exists"); 
//	    }
//	}	
	@PostMapping("/addStudent")
	public ResponseEntity<?> addStudent(@RequestBody Student stud) {
	    try {
	        // Validate mandatory fields
	        if (stud.getStudName() == null || stud.getStudName().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student name is required");
	        }

	        if (stud.getPhNo() <= 0) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone number is invalid");
	        }

	        if (stud.getEmailId() == null || stud.getEmailId().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ID is required");
	        }

	        // Normalize the email and check for uniqueness
	        String email = stud.getEmailId().trim().toLowerCase();
	        if (studDao.getStudentByEmail(email) != null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ID already exists");
	        }

	        // Validate the course_id
	        Course course = courseDao.getCourseById(stud.getCourseId());
	        if (course == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid course ID");
	        }

	        // Set the course and save the student
	        stud.setCourse(course);
	        Student newStudent = studDao.addStudent(stud);

	        return ResponseEntity.ok(newStudent);

	    } catch (Exception e) {
	        System.err.println("Error in addStudent: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	    }
	}


	@PutMapping("updateStudent")
	public Student updateStudent(@RequestBody Student stud) {
		return studDao.updateStudent(stud);
	}
	
	@DeleteMapping("deleteStudentById/{id}")
	public String deleteStudentById(@PathVariable("id") int studId) {
		studDao.deleteStudentById(studId);
		return "Employee Record Deleted Successfully!!!";
	}
}

