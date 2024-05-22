package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import org.mindrot.jbcrypt.BCrypt;


@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studId;
	private String studName;
	private long phNo;
	private String gender;
	private String country;
	private Date doj;
	@Column(unique =  true)
	private String emailId;
	private String password;
	private String otp;
	
	@ManyToOne
	@JoinColumn(name = "courseId", nullable = false)  // Enforce `NOT NULL`
	private Course courses;  
	
	public Student() {
	}

	public Student(int studId, String studName, long phNo, String gender, String country, Date doj, String emailId,
			String password) {
		this.studId = studId;
		this.studName = studName;
		this.phNo = phNo;
		this.gender = gender;
		this.country = country;
		this.doj = doj;
		this.emailId = emailId;
		this.password = password;
	}
	
	

	public Course getCourse() {
		return courses;
	}
	public void setCourse(Course course) {
		this.courses = course;
	}

	public int getStudId() {
		return studId;
	}
	public void setStudId(int studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}

	public long getPhNo() {
		return phNo;
	}
	public void setPhNo(long phNo) {
		this.phNo = phNo;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
		
	}
	 public int getCourseId() {
	        return courses.getCourseId();  // Ensure `Course` has a `getCourseId()` method
	    }
	
	public String getOtp() {
	    return otp;
	}

	public void setOtp(String otp) {
	    this.otp = otp;
	}
	public void setPassword(String password) {
//	    this.password = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
	    this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public boolean checkPassword(String passwordToCheck) {
	    return BCrypt.checkpw(passwordToCheck, this.password);
	}


	@Override
	public String toString() {
		return "Student [studId=" + studId + ", studName=" + studName + ", phNo=" + phNo + ", gender=" + gender
				+ ", country=" + country + ", doj=" + doj + ", emailId=" + emailId + ", password=" + password
				+ ", courses=" + courses + "]";
	}

	
}