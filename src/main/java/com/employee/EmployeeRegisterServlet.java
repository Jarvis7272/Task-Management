package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class EmployeeRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Retrieve form data
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String gender = req.getParameter("gender");
		String employeeType = req.getParameter("employeeType");
		String employeeDomain = req.getParameter("employeeDomain");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String mobile = req.getParameter("mobile");
		String address = req.getParameter("address");
		String manager_id = req.getParameter("manager_id"); // Retrieve the managerId from the form

		HttpSession session = req.getSession();
		try (Connection con = com.dbconnection.connectionprovider.connectionp()) {
			String query = "INSERT INTO employees (first_name, last_name, gender, employee_type,employee_domain, email, password, mobile, address, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, gender);
			ps.setString(4, employeeType);
			ps.setString(5, employeeDomain);
			ps.setString(6, email);
			ps.setString(7, password);
			ps.setString(8, mobile);
			ps.setString(9, address);

			// Validate and set manager_id
			if (manager_id != null && !manager_id.isEmpty()) {
				try {
					ps.setInt(10, Integer.parseInt(manager_id));
				} catch (NumberFormatException e) {
					// Handle invalid manager_id, e.g., set to null or default value
					ps.setNull(10, java.sql.Types.INTEGER);
				}
			} else {
				ps.setNull(10, java.sql.Types.INTEGER); // Set to null if no manager is selected
			}

			int affectedRows = ps.executeUpdate();
			int employeeId = 0;
			// Retrieve the generated employee ID
			if (affectedRows > 0) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						employeeId = generatedKeys.getInt(1);
					}
				}
				session.setAttribute("SuccessMessage", "Employee Added successfully. Employee ID: "+employeeId);
			} else {
				session.setAttribute("WarningMessage", "Error adding employee! Please try again.");
			}
			
			res.sendRedirect("hr-home.jsp");
		} catch (SQLException ex) {
			ex.printStackTrace();
			session.setAttribute("DangerMessage", "Database error occurred.");
			res.sendRedirect("hr-home.jsp");
		}
	}
}
