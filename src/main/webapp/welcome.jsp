<%@page import="com.abi.model.StudentModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	integrity="sha512-jR3z2Mw7YTwhMkE9gvZ6kt7rs5zm5ZNC8juii7C6FwXq0an8GmK7FgIs7/eRK3hiq0bwMkf8KxWsXau4gN1a3w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>

<style>
#profileModel {
	margin-top: 40px;
}

.avatar {
	width: 100px; /* Adjust width as needed */
	height: 100px; /* Adjust height as needed */
	border-radius: 50%; /* Make it circular */
	border: 2px solid #ccc; /* Add a border */
	object-fit: cover; /* Maintain aspect ratio and cover container */
}

#save {
	background-color: green;
	color: white; /* Text color */
	/* Add any additional styling as needed */
}
#back {
	background-color: red;
	color: white; /* Text color */
	/* Add any additional styling as needed */
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<!-- Logo -->
			<a class="navbar-brand" href="#">Logo</a>
			<!-- Toggler button for small screens -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- Navbar items -->
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="Register.jsp">Sign
							Up</a></li>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Logout</a></li>
					<li class="nav-item"><a class="nav-link" href="#!"
						data-toggle="modal" data-target="#profileModel">Profile</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-header">Welcome</div>
					<div class="card-body">
						<p>Welcome to the Home page</p>
						<a><%=(String) session.getAttribute("username")%> </a> <a
							href="login.jsp" class="btn btn-primary">Logout</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="profileModel" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Profile</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container text-center">
						<img src="https://www.w3schools.com/howto/img_avatar.png"
							alt="Avatar" class="avatar">
						<h5 class="modal-title" id="exampleModalLabel"><%=(String) session.getAttribute("username")%></h5>
						<div class="profile-Detiles">
							<table class="table">
								<tr>
									<th scope="row">First Name</th>
									<td><%=(String) session.getAttribute("firstname")%></td>
								</tr>
								<tr>
									<th scope="row">Last Name</th>
									<td><%=(String) session.getAttribute("lastname")%></td>
								</tr>
								<tr>
									<th scope="row">User Name</th>
									<td><%=(String) session.getAttribute("username")%></td>
								</tr>
								<tr>
									<th scope="row">Email</th>
									<td><%=(String) session.getAttribute("email")%></td>
								</tr>
								<tr>
									<th scope="row">Phone Number</th>
									<td><%=(String) session.getAttribute("phoneNumber")%></td>
								</tr>
								<tr>
									<th scope="row">Gender</th>
									<td><%=(String) session.getAttribute("gender")%></td>
								</tr>
								<tr>
									<th scope="row">Birthday</th>
									<td><%=session.getAttribute("dob") != null ? ((java.time.LocalDate) session.getAttribute("dob")).toString() : ""%></td>
								</tr>
								<tr>
									<th scope="row">Subject</th>
									<td><%=(String) session.getAttribute("subject")%></td>
								</tr>
							</table>
						</div>
						<div class="profile-edit" style="display: none;">
							<h3 class="mt-2">Please Edit Carefully</h3>
							<form action="EditProfileServlet" method="post">
								<table class="table">

									<tr>
										<td>First Name</td>
										<td><input type="text" class="form-control"
											name="firstname"
											value="<%=(String) session.getAttribute("firstname")%>"></td>
									</tr>
									<tr>
										<td>Last Name</td>
										<td><input type="text" class="form-control"
											name="lastname"
											value="<%=(String) session.getAttribute("lastname")%>"></td>
									</tr>
									<tr>
										<td>Email</td>
										<td><input type="email" class="form-control" name="email"
											value="<%=(String) session.getAttribute("email")%>"></td>
									</tr>
									<tr>
										<td>Phone Number</td>
										<td><input type="text" class="form-control"
											name="phonenumber"
											value="<%=(String) session.getAttribute("phoneNumber")%>"></td>
									</tr>
									<tr>
										<td>Gender</td>
										<td><input type="text" class="form-control" name="gender"
											value="<%=(String) session.getAttribute("gender")%>"></td>
									</tr>
									<tr>
										<td>Birthday</td>
										<td><input type="Date" class="form-control" name="dob"
											value="<%=session.getAttribute("dob") != null ? ((java.time.LocalDate) session.getAttribute("dob")).toString() : ""%>"></td>
									</tr>
									<tr>
										<td>Subject</td>
										<td><input type="text" class="form-control"
											name="subject"
											value="<%=(String) session.getAttribute("subject")%>"></td>
									</tr>
								</table>
								<div class="text-center">
									<button id="save"type="submit" class="btn btn-primary">Save</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="back"type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button id="edit-profile" type="button" class="btn btn-primary">Edit</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			let editProfile = false;
			$("#edit-profile").click(function() {
				if (editProfile) {
					$(".profile-Detiles").show();
					$(".profile-edit").hide();
					editProfile = false;
					$("#edit-profile").text("Edit");
				} else {
					$(".profile-Detiles").hide();
					$(".profile-edit").show();
					editProfile = true;
					$("#edit-profile").text("Back");
				}
			});
		});
	</script>

</body>
</html>
