<%@page import="com.taskmanagement.taskManager"%>
<%@page import="com.taskmanagement.taskManagerDao"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<!DOCTYPE html>
<html>
<head>
<title>Task Planner</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="Assets/css/home-style.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
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
		<% String first_name = (String)session.getAttribute("first_name"); %>
			<li>
				<div class="home-btn" onclick="toggleHomeMenu(event)">
					<a href="#" class="active"><%= first_name %></a>
				</div>
				<ul class="dropdown">
					<li><a href="profile.jsp">Profile</a></li>
					<li><a href="contact.jsp">Contact</a></li>
					<li><a href="logout">Logout</a></li>
				</ul>
			</li>
			<li class="home-dropdown-mobile"><a href="profile.jsp">Profile</a></li>
			<li class="home-dropdown-mobile"><a href="contact.jsp">Contact</a></li>
			<li class="home-dropdown-mobile"><a href="logout">Logout</a></li>
		</ul>
	</div>
	<div class="buttons">
		<button onclick="showForm('addForm')">
			<i class="fas fa-plus"></i> Add Task
		</button>
		<button onclick="showForm('updateForm')">
			<i class="fas fa-edit"></i> Update Task
		</button>
	</div>
	<div class="table-container">
		<h2>Task Management System</h2>
		<%@include file="Assets/Alerts/success-message.jsp"%>
		<%@include file="Assets/Alerts/warning-message.jsp"%>
		<%@include file="Assets/Alerts/failed-message.jsp"%>
		<table>
			<tr>
				<th>ID</th>
				<th>Task Name</th>
				<th>Assigned to</th>
				<th>Status</th>
				<th>Due date</th>
				<th>Start date</th>
				<th>End date</th>
				<th>Priority</th>
			</tr>
			<%
			Integer employee_id = (Integer) session.getAttribute("employee_id");
			taskManagerDao tasks = new taskManagerDao();
			List<taskManager> taskManager = tasks.getAllResourceTask(employee_id);

			for (taskManager task : taskManager) {
			%>
			<tr>
				<td><%=task.getTask_id()%></td>
				<td><%=task.getTask_name()%></td>
				<td><%=task.getAssigned_to()%></td>
				<td><%=task.getStatus()%></td>
				<td><%=task.getDue_date()%></td>
				<td><%=task.getStart_date()%></td>
				<td><%=task.getEnd_date()%></td>
				<td><%=task.getPriority()%></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
	<div id="addForm" class="form-container">
		<div class="form-content">
			<button class="close-btn" onclick="closeForm('addForm')">&times;</button>
			<h2>Add Task</h2>
			<form action="addTask" method="post">
				<div class="form-group">
					<label for="task_name">Task Name:</label> <input type="text"
						id="task_name" name="task_name" placeholder="Task Name" required>
				</div>
				<div class="form-group">
					<label for="assigned_to">Assigned To:</label> <input type="number"
						id="assigned_to" name="assigned_to" placeholder="Assigned To">
				</div>
				<div class="form-group">
					<label for="status">Status:</label> <select id="status"
						name="status" required>
						<option value="">Status</option>
						<option value="In Progress">In Progress</option>
						<option value="Waiting">Waiting</option>
						<option value="Completed">Completed</option>
						<option value="Cancelled">Cancelled</option>
					</select>
				</div>
				<div class="form-group">
					<label for="due_date">Due Date:</label> <input type="date"
						id="due_date" name="due_date" required>
				</div>
				<div class="form-group">
					<label for="start_date">Start Date:</label> <input type="date"
						id="start_date" name="start_date" readonly>
				</div>
				<div class="form-group">
					<label for="end_date">End Date:</label> <input type="date"
						id="end_date" name="end_date" readonly>
				</div>
				<div class="form-group">
					<label for="priority">Priority:</label> <select id="priority"
						name="priority" required>
						<option value="">Priority</option>
						<option value="Low">Low</option>
						<option value="Medium">Medium</option>
						<option value="High">High</option>
					</select>
				</div>
				<div class="form-group">
					<button type="submit">Submit</button>
				</div>
			</form>
		</div>
	</div>

	<div id="updateForm" class="form-container">
		<div class="form-content">
			<button class="close-btn" onclick="closeForm('updateForm')">&times;</button>
			<h2>Update Task</h2>
			<form action="updateTask" method="post">
				<div class="form-group">
					<label for="task_id">Task ID:</label> <input type="number"
						id="task_id" name="task_id" required>
				</div>
				<div class="form-group">
					<label for="status">Status:</label> <select id="status"
						name="status">
						<option value="">Status</option>
						<option value="In Progress">In Progress</option>
						<option value="Waiting">Waiting</option>
						<option value="Completed">Completed</option>
						<option value="Cancelled">Cancelled</option>
					</select>
				</div>
				<div class="form-group">
					<label for="start_date">Start Date:</label> <input type="date"
						id="start_date" name="start_date">
				</div>
				<div class="form-group">
					<label for="end_date">End Date:</label> <input type="date"
						id="end_date" name="end_date">
				</div>
				<div class="form-group">
					<button type="submit">Update</button>
				</div>
			</form>
		</div>
	</div>
	<script src="Assets/js/script.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
