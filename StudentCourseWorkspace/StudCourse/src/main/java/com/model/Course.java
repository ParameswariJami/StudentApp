package com.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {
	
	@Id
	private int courseId;
	private String courseName;
	private String facultyName;
	
	@OneToMany(mappedBy="courses")
	@JsonIgnore    //Restricting the user to fetch the employee details when the user is fetching department
	List<Student> studList = new ArrayList<Student>();
	
	public Course() {
	}

	public Course(int courseId, String courseName, String facultyName) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.facultyName = facultyName;
	}
	
	
	public List<Student> getStudList() {
		return studList;
	}
	public void setStudList(List<Student> studList) {
		this.studList = studList;
	}

	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getFacultyName() {
		return facultyName;
	}
	public void setLocation(String facultyName) {
		this.facultyName = facultyName;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", facultyName=" + facultyName + ", studList="
				+ studList + "]";
	}

	
}
