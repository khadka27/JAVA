
package com.abi.controller.serv;

import java.io.IOException;
import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

import com.abi.controller.DatabaseController;
import com.abi.model.StudentModel;
import com.abi.utils.studentUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(asyncSupported = true, urlPatterns = { studentUtils.REGISTER_SERVLET })
public class RegisterServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController db = new DatabaseController();

	public RegisterServlets() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstname = request.getParameter(studentUtils.FIRST_NAME);
		String lastname = request.getParameter(studentUtils.LAST_NAME);
		String username = request.getParameter(studentUtils.USERNAME);
		String dobString = request.getParameter(studentUtils.BIRTHDAY);
		LocalDate dob = LocalDate.parse(dobString);
		String gender = request.getParameter(studentUtils.GENDER);
		String email = request.getParameter(studentUtils.EMAIL);
		String phoneNumber = request.getParameter(studentUtils.PHONE_NUMBER);
		String subject = request.getParameter(studentUtils.SUBJECT);
		String password = request.getParameter(studentUtils.PASSWORD);
		String repassword = request.getParameter(studentUtils.RE_PASSWORD);
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		StudentModel.setPassword(hashedPassword);

		// Validation for empty fields
		if (firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || dobString.isEmpty() || gender.isEmpty()
				|| email.isEmpty() || phoneNumber.isEmpty() || subject.isEmpty() || password.isEmpty()
				|| (repassword != null && repassword.isEmpty())) {
//            response.sendRedirect(request.getContextPath() + "/Register.jsp?error=empty");
			redirectToRegistrationPage(request, response, "Please fill all the fields.");
			return; // Return from the method to stop further execution
		}

		// Validation for name format
		if (!isValidName(firstname) || !isValidName(lastname)) {
//            response.sendRedirect(request.getContextPath() + "/Register.jsp?error=name");
			redirectToRegistrationPage(request, response, "Invalid first name or last name format.");
			return;
		}

		// Validation for username format
		if (!isValidUsername(username)) {
//            response.sendRedirect(request.getContextPath() + "/Register.jsp?error=username");
			redirectToRegistrationPage(request, response, "Invalid username format.");
			return;
		}

		// 3. Birthday Date Restriction
		if (dob.isAfter(LocalDate.now())) {
			// Redirect to the registration page with an error message
//            response.sendRedirect(request.getContextPath() + "/Register.jsp?error=dob");
			redirectToRegistrationPage(request, response, "Invalid date of birth.");
			return;
		}

		// Validation for email format
		if (!email.contains("@") || !email.contains(".")) {
//			response.sendRedirect(request.getContextPath() + "/Register.jsp?error=email");
			redirectToRegistrationPage(request, response, "Invalid email format.");

			return;
		}

		// Validation for phonenumber format
		if (!isValidPhoneNumber(phoneNumber)) {
//			response.sendRedirect(request.getContextPath() + "/Register.jsp?error=phonenumber");
			redirectToRegistrationPage(request, response, "Invalid phone number format.");
			return;
		}

		// Check if the phone number already exists in the database
		if (db.isPhoneNumberExists(phoneNumber)) {
//            response.sendRedirect(request.getContextPath() + "/Register.jsp?error=phone_exists");
			redirectToRegistrationPage(request, response, "Phone number already exists.");
			return;
		}

		// Check if the email already exists in the database
		if (db.isEmailExists(email)) {
//            response.sendRedirect(request.getContextPath() + "/Register.jsp?error=email_exists");
			redirectToRegistrationPage(request, response, "Email already exists.");
			return;
		}

		// Check if the username already exists in the database
		if (db.isUsernameExists(username)) {
//            response.sendRedirect(request.getContextPath() + "/Register.jsp?error=username_exists");
			redirectToRegistrationPage(request, response, "Username already exists.");
			return;
		}

		// Password complexity and match validation
		if (!isValidPassword(password, repassword)) {
//            response.sendRedirect(request.getContextPath() + "/Register.jsp?error=password");
			redirectToRegistrationPage(request, response, "Invalid password format or passwords don't match.");
			return;
		}

		// If all validations pass, proceed with adding the student
		StudentModel studentModel = new StudentModel(firstname, lastname, username, dob, gender, email, phoneNumber,
				subject, hashedPassword);
		int result = db.addStudent(studentModel);
//        if (result > 0) {
//            response.sendRedirect(request.getContextPath() + "/login.html");
//        } else {
//            System.out.println("Error occurred while adding student to the database");
//            response.sendRedirect(request.getContextPath() + "/Register.html?error=dberror");
//        }

		if (result == 1) {
			// Storing all in session
			HttpSession session = request.getSession();
			session.setAttribute("firstname", firstname);
			session.setAttribute("lastname", lastname);
			session.setAttribute("username", username);
			session.setAttribute("dob", dob);
			session.setAttribute("gender", gender);
			session.setAttribute("email", email);
			session.setAttribute("phoneNumber", phoneNumber);
			session.setAttribute("subject", subject);
			// add time on the session 30 minutes
			session.setMaxInactiveInterval(30 * 60);

			request.setAttribute(studentUtils.ERROR_MESSAGE, studentUtils.SUCCESS_REGISTER_MESSAGE);
			response.sendRedirect(request.getContextPath() + studentUtils.LOGIN_PAGE);
		} else if (result == 0) {
			// Redirect to the same register page with form data mistake
//        	String studentUtils.ERROR_MESSAGE = "Please correct the form data. ";
			request.setAttribute(studentUtils.ERROR_MESSAGE, studentUtils.REGISTER_ERROR_MESSAGE);
			request.getRequestDispatcher(request.getContextPath() + studentUtils.REGISTER_PAGE).forward(request,
					response);
		} else {
			// Redirect to the same register page with server error
//        	String errorMessage = "An unexpected server error occurred. ";
			request.setAttribute(studentUtils.ERROR_MESSAGE, studentUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(request.getContextPath() + studentUtils.REGISTER_PAGE).forward(request,
					response);
		}
	}

// Helper methods for validations
	private boolean isValidName(String name) {
		// Implement name validation logic
		return !name.matches(".*\\d.*") && !name.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
	}

//private boolean isValidUsername(String username) {
//    // Implement username validation logic
//    return username.length() > 6 && !username.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
//}

	private boolean isValidUsername(String username) {
		// Implement username validation logic
		return username.length() > 6 && !username.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")
				&& username.matches(".*[a-zA-Z0-9].*");
	}

	private void redirectToRegistrationPage(HttpServletRequest request, HttpServletResponse response,
			String errorMessage) throws ServletException, IOException {
		request.setAttribute(studentUtils.ERROR_MESSAGE, errorMessage);
		request.getRequestDispatcher(studentUtils.REGISTER_PAGE).forward(request, response);
	}

	private boolean isValidPassword(String password, String repassword) {
		// Implement password validation logic
		return password.length() > 6 && // Checks if password length is greater than 6
				password.matches(".*\\d.*") && // Checks if password contains at least one digit
				password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*") && // Checks if password contains
																						// at least one special
																						// character
				password.matches(".*[A-Z].*") && // Checks if password contains at least one uppercase letter
				password.equals(repassword); // Checks if password matches the re-entered password
	}

//phone number validation
	private boolean isValidPhoneNumber(String phoneNumber) {
		// Implement phone number validation logic
		return phoneNumber.startsWith("+") && phoneNumber.length() == 14;
	}

}
