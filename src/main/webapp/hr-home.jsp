<%@page import="com.employee.Employee"%>
<%@page import="com.employee.EmployeeDao"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <title>Task Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="Assets/css/home-style.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
</head>
<body>
    <div class="navbar">
        <div class="logo"><img src="Assets/images/logo.png" /></div>
        <div class="hamburger" onclick="toggleMenu()">
            <i class="fas fa-bars"></i>
        </div>
        <ul class="menu">
            <li>
                <div class="home-btn" onclick="toggleHomeMenu(event)">
                    <a href="#" class="active">Home</a>
                </div>
                <ul class="dropdown">
                    <li><a href="#">Profile</a></li>
            		<li><a href="#">Contact</a></li>
                    <li><a href="logout">Logout</a></li>
                </ul>
            </li>
            <li class="home-dropdown-mobile"><a href="#">Profile</a></li>
            <li class="home-dropdown-mobile"><a href="#">Contact</a></li>
            <li class="home-dropdown-mobile"><a href="#">Logout</a></li>
        </ul>
    </div>
		<div class="buttons">
        <button onclick="showForm('addForm')"><i class="fas fa-plus"></i> Add Employee</button>
        <button onclick="showForm('updateForm')"><i class="fas fa-edit"></i> Update Employee</button>
        <button onclick="showForm('deleteForm')"><i class="fas fa-trash"></i> Delete Employee</button>
    </div>
    <div class="table-container">
        <h2>Employee Management System</h2>
        <table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Gender</th>
        <th>Employee Type</th>
        <th>Email</th>
        <th>Password</th>
        <th>Mobile</th>
        <th>Address</th>
        <th>Manager ID</th>
    </tr>
    <%	
    	EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employees = employeeDao.getEmployees();

        for (Employee employee : employees) {
    %>
    <tr>
        <td><%= employee.getId() %></td>
        <td><%= employee.getFirstName() %></td>
        <td><%= employee.getLastName() %></td>
        <td><%= employee.getGender() %></td>
        <td><%= employee.getEmployeeType() %></td>
        <td><%= employee.getEmail() %></td>
        <td><%= employee.getPassword() %></td>
        <td><%= employee.getMobile() %></td>
        <td><%= employee.getAddress() %></td>
        <td><%= employee.getManagerId() %></td>
    </tr>
    <%
        }
    %>
</table>
    </div>

    <div id="addForm" class="form-container">
        <div class="form-content">
            <button class="close-btn" onclick="closeForm('addForm')">&times;</button>
            <h2>Add Employee</h2>
            <form action="register" method="post">
                <div class="form-group">
                    <input type="text" name="firstName" placeholder="First Name" required>
                </div>
                <div class="form-group">
                    <input type="text" name="lastName" placeholder="Last Name" required>
                </div>
                <div class="form-group">
                    <select name="gender" required>
                        <option value="">Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <div class="form-group">
                    <select name="employeeType" required>
                        <option value="">Employee Type</option>
                        <option value="Resource">Resource</option>
                        <option value="Lead">Lead</option>
                        <option value="Manager">Manager</option>
                        <option value="HR">HR</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="email" name="email" placeholder="Email Address" required>
                </div>
                <div class="form-group">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <input type="text" name="mobile" placeholder="Mobile" required>
                </div>
                <div class="form-group">
                    <input type="text" name="address" placeholder="Address" required>
                </div>
                <div class="form-group">
                    <input type="text" name="manager_id" placeholder="Manager ID">
                </div>
                <div class="form-group">
                    <button type="submit">Register</button>
                </div>
            </form>
        </div>
    </div>

    <div id="updateForm" class="form-container">
        <div class="form-content">
            <button class="close-btn" onclick="closeForm('updateForm')">&times;</button>
            <h2>Update Employee</h2>
            <form action="update" method="post">
                <div class="form-group">
                    <input type="text" name="id" placeholder="Employee ID" required>
                </div>
                <div class="form-group">
                    <input type="text" name="firstName" placeholder="First Name" required>
                </div>
                <div class="form-group">
                    <input type="text" name="lastName" placeholder="Last Name" required>
                </div>
                <div class="form-group">
                    <select name="gender" required>
                        <option value="">Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <div class="form-group">
                    <select name="employeeType" required>
                        <option value="">Employee Type</option>
                        <option value="Resource">Resource</option>
                        <option value="Lead">Lead</option>
                        <option value="Manager">Manager</option>
                        <option value="HR">HR</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="email" name="email" placeholder="Email Address" required>
                </div>
                <div class="form-group">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <input type="text" name="mobile" placeholder="Mobile" required>
                </div>
                <div class="form-group">
                    <input type="text" name="address" placeholder="Address" required>
                </div>
                <div class="form-group">
                    <input type="text" name="manager_id" placeholder="Manager ID">
                </div>
                <div class="form-group">
                    <button type="submit">Update</button>
                </div>
            </form>
        </div>
    </div>

    <div id="deleteForm" class="form-container">
        <div class="form-content">
            <button class="close-btn" onclick="closeForm('deleteForm')">&times;</button>
            <h2>Delete Employee</h2>
            <form action="delete" method="post">
                <div class="form-group">
                    <input type="text" name="id" placeholder="Employee ID" required>
                </div>
                <div class="form-group">
                    <button type="submit">Delete</button>
                </div>
            </form>
        </div>
    </div>
    <script src="Assets/js/script.js"></script>
</body>
</html>
