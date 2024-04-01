package com.abi.model;

import java.time.LocalDate;



public class StudentModel {
	private String firstname;
	private String lastname;
	private String username;
	private LocalDate dob;
	private String gender;
	private String email;
	private String phoneNumber;
	private String subject;
	private String password;

	public StudentModel(String firstname, String lastname, String username, LocalDate dob, String gender, String email,
			String phoneNumber, String subject, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.subject = subject;
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public String getUsername() {
		return username;
	}

	public LocalDate getDob() {
		return dob;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getSubject() {
		return subject;
	}

	public String getPassword() {
		return password;
	}

	public static void setPassword(String hashedPassword) {
		// TODO Auto-generated method stub

	}
	
	

}