package com.taskmanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.dbconnection.connectionprovider;
import com.employee.Employee;
import com.employee.EmployeeDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addTask")
public class TaskAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String task_name = req.getParameter("task_name");
		int assigned_to = Integer.parseInt(req.getParameter("assigned_to"));
		String status = req.getParameter("status");
		String due_date = req.getParameter("due_date");
		String priority = req.getParameter("priority");

		java.sql.Date dueDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date parsedDate = dateFormat.parse(due_date);
			dueDate = new java.sql.Date(parsedDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		HttpSession session = req.getSession();
		String employee_type = (String) session.getAttribute("employee_type");
		Integer employee_id = (Integer) session.getAttribute("employee_id");
		
		// Task assignment range 
		EmployeeDao employeeDao = new EmployeeDao();
		List<Employee> employees = employeeDao.getEmployeesByEmployeeId(employee_id);
		
		boolean canAssign = false;
		for (Employee emp : employees) {
		    if (emp.getId() == assigned_to) {
		        canAssign = true;
		        break;
		    }
		}
		
		if (employee_id == assigned_to) {
		    canAssign = true;
		}
		
		if (!canAssign) {
		    session.setAttribute("WarningMessage", "You can only assign tasks to yourself or your subordinates.");
		    res.sendRedirect("home");
		    return;
		}

		if (!("resource".equalsIgnoreCase(employee_type) || "lead".equalsIgnoreCase(employee_type) || "manager".equalsIgnoreCase(employee_type))) {
			session.setAttribute("WarningMessage", "You are not authorized to add tasks.");
			res.sendRedirect("home");
			return;
		}

		// Task adding setup
		try (Connection con = connectionprovider.connectionp()) {
			String empCheckQuery = "SELECT employee_type FROM employees WHERE employee_id = ?";
			PreparedStatement empCheckStmt = con.prepareStatement(empCheckQuery);
			empCheckStmt.setInt(1, assigned_to);
			ResultSet empCheckResult = empCheckStmt.executeQuery();

			if (empCheckResult.next()) {
				String assignedEmployeeType = empCheckResult.getString("employee_type");

				if ("HR".equalsIgnoreCase(assignedEmployeeType)) {
					session.setAttribute("WarningMessage", "Tasks cannot be assigned to HR.");
					res.sendRedirect("home");
					return;
				}

				String query = "INSERT INTO task_manager(task_name, assigned_to, status, due_date, priority) VALUES (?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, task_name);
				ps.setInt(2, assigned_to);
				ps.setString(3, status);
				ps.setDate(4, dueDate);
				ps.setString(5, priority);

				int affectedRows = ps.executeUpdate();
				int taskId = 0;

				if (affectedRows > 0) {
					try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							taskId = generatedKeys.getInt(1);
						}
					}
					session.setAttribute("SuccessMessage", "Task added successfully. Task ID: " + taskId);
					res.sendRedirect("home");
				} else {
					session.setAttribute("WarningMessage", "Error adding task! Please try again.");
					res.sendRedirect("home");
				}

			} else {
				session.setAttribute("WarningMessage", "Assigned employee ID does not exist.");
				res.sendRedirect("home");
			}


		} catch (SQLException e) {
			e.printStackTrace();
			session.setAttribute("DangerMessage", "Database error occurred.");
			res.sendRedirect("home");
		}
	}
}
