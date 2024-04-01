package com.abi.controller.serv;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.abi.controller.DatabaseController;
import com.abi.model.StudentModel;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phonenumber");
        String subject = request.getParameter("subject");
        
        HttpSession session = request.getSession();
     // Retrieve the StudentModel object from the session
     // Retrieve the username string from the session attribute
        String username = (String) session.getAttribute("username");

        // Check if the username is not null
        if (username != null) {
            try {
                // Retrieve the StudentModel object associated with the username from the database
                StudentModel student = DatabaseController.getStudentByUsername(username);
                
                // Rest of your logic...
                if (student != null) {
                	
            		// Storing all in session
        			HttpSession session1 = request.getSession();
        			session1.setAttribute("firstname", firstname);
        			session1.setAttribute("lastname", lastname);
        			session1.setAttribute("username", username);
        			session1.setAttribute("dob", dob);
        			session1.setAttribute("gender", gender);
        			session1.setAttribute("email", email);
        			session1.setAttribute("phoneNumber", phoneNumber);
        			session1.setAttribute("subject", subject);
        			//add time on the session 30 minutes
        			session.setMaxInactiveInterval(30 * 60);
                    // Student found in the database, proceed with your logic
                    
                    // For example:
                    // Update the student model with new information
                    student.setFirstname(firstname);
                    student.setLastname(lastname);
                    student.setDob(dob);
                    student.setGender(gender);
                    student.setEmail(email);
                    student.setPhoneNumber(phoneNumber);
                    student.setSubject(subject);
                    
                    // Attempt to update the user's profile
                    boolean updateSuccess = DatabaseController.updateUser(student);
                    
                    if (updateSuccess) {
                        // Update successful
                        session.setAttribute("username", username); // Update session attribute
                        response.sendRedirect("welcome.jsp"); // Redirect to profile page
                    } else {
                        // Update failed
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    // Student not found in the database
                    response.sendRedirect("error.jsp");
                }
            } catch (Throwable e) {
                // Handle specific exceptions here
                e.printStackTrace(); // Print stack trace for debugging
                response.sendRedirect("error.jsp");
            }
        } else {
            // Handle the case when the username is null
            response.sendRedirect("login.jsp"); // Redirect to login page
            System.out.println("Username not found in session");
        }
    }
  }


