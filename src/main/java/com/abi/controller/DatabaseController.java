package com.abi.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.abi.model.StudentModel;
import com.abi.utils.StringUtil;

import jakarta.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.mindrot.jbcrypt.BCrypt;
import com.abi.controller.DatabaseController;

public class DatabaseController {

	public static Connection getConn() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = ("jdbc:mysql://localhost:3306/collage_apps");
		String username = "root";
		String password = "";
		System.out.println("Connected to database");
		return DriverManager.getConnection(url, username, password);
	}

	public int addStudent(StudentModel studentModel) {
		try (Connection conn = getConn()) {
			PreparedStatement ps = conn.prepareStatement(StringUtil.Insert_Student);

			ps.setString(1, studentModel.getFirstname());
			ps.setString(2, studentModel.getLastname());
			ps.setString(3, studentModel.getUsername());
//    ps.setDate(4, Date.valueOf(studentModel.getDob()));
			ps.setDate(4, java.sql.Date.valueOf(studentModel.getDob()));
			ps.setString(5, studentModel.getGender());
			ps.setString(6, studentModel.getEmail());
			ps.setString(7, studentModel.getPhoneNumber());
			ps.setString(8, studentModel.getSubject());
			ps.setString(9, studentModel.getPassword());
			int i = ps.executeUpdate();
			return i;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public int getStudentLoginInfo(String username, String password) {
		try (Connection con = getConn()) {
			PreparedStatement ps = con.prepareStatement(StringUtil.GET_LOGIN_STUDENT_INFOS);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// User name and password match in the database
				return 1;
			} else {
				// No matching record found
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;

		}
	}

	// username already exists
	public boolean isUsernameExists(String username) {
		try (Connection con = getConn()) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM student_infos WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true; // Username exists
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Handle or log the exception properly
		}
		return false; // Username does not exist
	}

//        isEmailExists
	public boolean isEmailExists(String email) {
		try (Connection con = getConn()) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM student_infos WHERE email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// isPhoneNumberExists
	public boolean isPhoneNumberExists(String phoneNumber) {
		try (Connection con = getConn()) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM student_infos WHERE phoneNumber = ?");
			ps.setString(1, phoneNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public String getHashedPassword(String username) {
		try (Connection con = getConn()) {
			PreparedStatement st = con.prepareStatement(StringUtil.GET_HASHED_PASSWORD);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			/*
			 * String password = rs.getString("hashed_password"); System.out.println(
			 * password);
			 */
			if (rs.next()) {
				return rs.getString("password");
			} else {
				// Username not found
				return null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// Method to retrieve a StudentModel object based on the username

	public static StudentModel getStudentByUsername(String username) {

		try (Connection con = getConn()) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM student_infos WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Retrieve student information from the ResultSet and create a StudentModel
				// object
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				LocalDate dob = rs.getDate("dob").toLocalDate();
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phonenumber");
				String subject = rs.getString("subject");
				String password = rs.getString("password");

				return new StudentModel(firstname, lastname, username, dob, gender, email, phoneNumber, subject,
						password);
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Handle or log the exception properly
		}
		return null; // Return null if no student with the given username is found
	}

	public static boolean updateUser(StudentModel student) throws Throwable {
		boolean success = false;
		try (Connection con = getConn()) {
			String query = "UPDATE student_infos SET firstname = ?, lastname = ?, dob = ?, gender = ?, email = ?, phonenumber = ?, subject = ? WHERE username = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, student.getFirstname());
			ps.setString(2, student.getLastname());
			ps.setDate(3, java.sql.Date.valueOf(student.getDob()));
			ps.setString(4, student.getGender());
			ps.setString(5, student.getEmail());
			ps.setString(6, student.getPhoneNumber());
			ps.setString(7, student.getSubject());
			ps.setString(8, student.getUsername());
			int rowsUpdated = ps.executeUpdate();
			success = rowsUpdated > 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return success;
	}

	public static StudentModel getStudentByUsername(StudentModel username) {
		// TODO Auto-generated method stub
		return null;
	}

}
