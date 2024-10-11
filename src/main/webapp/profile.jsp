<%@ page import="com.employee.Employee"%>
<%@ page import="com.employee.EmployeeDao"%>
<%@ page import="java.util.List"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<title>Task Planner</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="Assets/css/home-style.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
</head>
<body>
	<div class="navbar">
		<div class="logo">
			<img src="Assets/images/logo.png" />
		</div>
		<div class="hamburger" onclick="toggleMenu()">
			<i class="fas fa-bars"></i>
		</div>
		<ul class="menu">
			<% String first_name = (String) session.getAttribute("first_name"); %>
			<% String email = (String) session.getAttribute("email"); %>
			<li>
				<div class="home-btn" onclick="toggleHomeMenu(event)">
					<a href="#" class="active"><%= first_name%></a>
				</div>
				<ul class="dropdown">
					<li><a href="home">Home</a></li>
					<li><a href="contact.jsp">Contact</a></li>
					<li><a href="logout">Logout</a></li>
				</ul>
			</li>
			<li class="home-dropdown-mobile"><a href="home">Home</a></li>
			<li class="home-dropdown-mobile"><a href="contact.jsp">Contact</a></li>
			<li class="home-dropdown-mobile"><a href="logout">Logout</a></li>
		</ul>
	</div>
	
	<div class="container">
	<%
		EmployeeDao employeeDao = new EmployeeDao();
		List<Employee> employees = employeeDao.getEmployees(email);
		for (Employee employee : employees) {
	%>
		<div class="row">
			<div class="col-12">
				<div class="content">
					<h2>Employee Profile Details</h2>
					<div class="profile-header">
						<div class="d-flex align-items-center">
						</div>
					</div>
					<div class="details-section">
						<div class="detail-item">
							<label class="form-label" for="id">ID</label>
							<p class="form-control"><%=employee.getId()%></p>
						</div>
						<div class="detail-item">
							<label class="form-label" for="firstName">First Name</label>
							<p class="form-control"><%=employee.getFirstName()%></p>
						</div>
						<div class="detail-item">
							<label class="form-label" for="lastName">Last Name</label>
							<p class="form-control"><%=employee.getLastName()%></p>
						</div>
						<div class="detail-item">
							<label class="form-label" for="gender">Gender</label>
							<p class="form-control"><%=employee.getGender()%></p>
						</div>
						<div class="detail-item">
							<label class="form-label" for="employeeType">Employee
								Type</label>
							<p class="form-control"><%=employee.getEmployeeType()%></p>
						</div>
						<div class="detail-item">
							<label class="form-label" for="employeeDomain">Employee
								Domain</label>
							<p class="form-control"><%=employee.getEmployeeDomain()%></p>
						</div>
						<div class="detail-item">
							<label class="form-label" for="email">Email</label>
							<p class="form-control"><%=employee.getEmail()%></p>
						</div>
						<div class="detail-item">
							<label class="form-label" for="mobile">Mobile</label>
							<p class="form-control"><%=employee.getMobile()%></p>
						</div>
						<div class="detail-item">
							<label class="form-label" for="address">Address</label>
							<p class="form-control"><%=employee.getAddress()%></p>
						</div>
						<div class="detail-item">
							<label class="form-label" for="managerId">Manager ID</label>
							<p class="form-control"><%=employee.getManagerId()%></p>
						</div>
						<% } %>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="Assets/js/script.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
