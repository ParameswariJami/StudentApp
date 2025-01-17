package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	 @Query("from Student e where e.studName = :name")
	    List<Student> findByName(@Param("name") String studName);

	 @Query("from Student s where s.emailId = :emailId")
	    Student findByEmail(@Param("emailId") String emailId);
	 
	    // Optional: Modify existing query
	    @Query("from Student e where e.emailId = :emailId and e.password = :password")
	    Student studLogin(@Param("emailId") String emailId, @Param("password") String password);
	}