package com.dao;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Student;

@Service
public class StudentDao {

	@Autowired
    private StudentRepository studRepo; 
	
//	public Student studLogin(String emailId, String password) {
//		return studRepo.studLogin(emailId, password);
//	}
	public Student studLogin(String emailId, String password) {
        Student student = studRepo.findByEmail(emailId);
        if (student != null && BCrypt.checkpw(password, student.getPassword())) {
            return student;
        }
        return null;  // If the student does not exist or password does not match
    }

	public List<Student> getAllStudents() {
		return studRepo.findAll();
	}

	public Student getStudentById(int studId) {
		return studRepo.findById(studId).orElse(null);
	}

	public List<Student> getStudentByName(String studName) {
		return studRepo.findByName(studName);
	}
	public Student getStudentByEmail(String emailId) {
        return studRepo.findByEmail(emailId);  // Retrieve student by email
    }



	public Student addStudent(Student stud) {
		return studRepo.save(stud);
	}
	
	public Student updateStudent(Student stud) {
		return studRepo.save(stud);
	}

	public void deleteStudentById(int studId) {
		studRepo.deleteById(studId);
	}
	
}
