package com.abi.utils;

import java.sql.PreparedStatement;

import org.apache.catalina.User;

public class StringUtil {

	public static final String Insert_Student = "INSERT INTO student_infos (firstname, lastname,username,dob,gender,email,phonenumber,subject,password) VALUES (?,?,?,?,?,?,?,?,?)";

	public static final String GET_LOGIN_STUDENT_INFOS = "SELECT username ,password from student_infos where username = ? And password = ?";

	public static final String GET_STUDENT = "SELECT * from student_infos";

	public static final String GET_HASHED_PASSWORD = "SELECT password FROM student_infos WHERE username = ?";

	
}
