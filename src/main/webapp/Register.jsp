<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #87ceeb; /* Sky Blue */
}

.form-container {
	margin-top: 73px;
	background-color: #E8EEEE;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.error {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #E8EEEE;
	border: 1px solid black;
	border-radius: 5px;
	padding: 20px;
	max-width: 300px;
	text-align: center;
}

/* Error message styles */
.error-message {
	color: #721c24;
	margin-bottom: 10px;
}

/* Close button styles */
.close-button {
	position: relative;
	top: 5px;
	right: 5px;
	cursor: pointer;
	background: transparent;
	border: none;
	font-size: 18px;
	color: red;
	padding: 0;
}

.close-button:hover {
	color: black;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<%@ include file="header.jsp"%>




	<div id="error" class="error">
		<%-- Display error message if it exists --%>
		<%
	        String errorMessage = (String) request.getAttribute("errorMessage");
	        if (errorMessage != null && !errorMessage.isEmpty()) {
	    %>
		<p class="error-message"><%=errorMessage%></p>
		<button id="close-button" class="close-button">OK</button>
		<%
	        }
	    %>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="form-container">
					<h2 class="mb-4">Register</h2>
					<form action="RegisterServlets" method="post">
						<div class="row mb-3">
							<div class="col-md-6">
								<label for="firstname" class="form-label">First Name</label> <input
									type="text" class="form-control" id="firstname"
									name="firstname" required>
							</div>
							<div class="col-md-6">
								<label for="lastname" class="form-label">Last Name</label> <input
									type="text" class="form-control" id="lastname" name="lastname"
									required>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-md-6">
								<label for="username" class="form-label">Username</label> <input
									type="text" class="form-control" id="username" name="username"
									required>
							</div>
							<div class="col-md-6">
								<label for="birthday" class="form-label">Birthday</label> <input
									type="date" class="form-control" id="birthday" name="dob"
									required>
							</div>
						</div>
						<div class="row mb-3">
							<div class="mb-3 gender-label">Gender</div>
							<div class="mb-3 form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="male" value="male"> <label class="form-check-label"
									for="male">Male</label>
							</div>
							<div class="mb-3 form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="female" value="female"> <label
									class="form-check-label" for="female">Female</label>
							</div>
							<div class="mb-3 form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="other" value="other"> <label
									class="form-check-label" for="other">Other</label>
							</div>
							<div class="col-md-6">
								<label for="email" class="form-label">Email</label> <input
									type="email" class="form-control" id="email" name="email"
									required>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-md-6">
								<label for="phonenumber" class="form-label">Phone Number</label>
								<input type="tel" class="form-control" id="phonenumber"
									name="phonenumber" required>
							</div>
							<div class="col-md-6">
								<label for="subject" class="form-label">Subject</label> <select
									class="form-select" id="subject" name="subject" required>
									<option value="">Select Subject</option>
									<option value="Maths">Maths</option>
									<option value="Science">Science</option>
									<option value="IT" selected>IT</option>
									<option value="Other">Other</option>
								</select>
							</div>

						</div>
						<div class="row mb-3">
							<div class="col-md-6">
								<label for="password" class="form-label">Password</label> <input
									type="password" class="form-control" id="password"
									name="password" required>
							</div>
							<div class="col-md-6">
								<label for="repassword" class="form-label">Confirm
									Password</label> <input type="password" class="form-control"
									id="repassword" name="repassword" required>
							</div>
						</div>
						<button type="submit" class="btn btn-primary">Register</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<%@ include file="footer.jsp"%>

</body>
<script>
		//JavaScript code to handle closing the error message
		document.addEventListener('DOMContentLoaded', function() {
		    // Get reference to the close button
		    var closeButton = document.getElementById('close-button');
		    
		    // Add event listener to the close button
		    closeButton.addEventListener('click', function() {
		        // Get reference to the error message container
		        var errorContainer = document.getElementById('error');
		        
		        // Hide the error message container
		        errorContainer.style.display = 'none';
		    });
		});
		</script>
		
		
		

</html>
