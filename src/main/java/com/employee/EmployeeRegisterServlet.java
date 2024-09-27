package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class EmployeeRegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // Retrieve form data
	    String firstName = req.getParameter("firstName");
	    String lastName = req.getParameter("lastName");
	    String gender = req.getParameter("gender");
	    String employeeType = req.getParameter("employeeType");
	    String email = req.getParameter("email");
	    String password = req.getParameter("password");
	    String mobile = req.getParameter("mobile");
	    String address = req.getParameter("address");
	    String manager_id = req.getParameter("manager_id");  // Retrieve the managerId from the form
	    
	    RequestDispatcher rd;

	    try(Connection con = com.dbconnection.connectionprovider.connectionp()) {
	        String query = "INSERT INTO employees (first_name, last_name, gender, employee_type, email, password, mobile, address, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(query);
	        
	        ps.setString(1, firstName);
	        ps.setString(2, lastName);
	        ps.setString(3, gender);
	        ps.setString(4, employeeType);
	        ps.setString(5, email);
	        ps.setString(6, password);
	        ps.setString(7, mobile);
	        ps.setString(8, address);

	        // Validate and set manager_id
	        if (manager_id != null && !manager_id.isEmpty()) {
	            try {
	                ps.setInt(9, Integer.parseInt(manager_id));
	            } catch (NumberFormatException e) {
	                // Handle invalid manager_id, e.g., set to null or default value
	                ps.setNull(9, java.sql.Types.INTEGER);
	            }
	        } else {
	            ps.setNull(9, java.sql.Types.INTEGER); // Set to null if no manager is selected
	        }

	        ps.executeUpdate();

	        // Redirect to a success page or back to registration
	        rd = req.getRequestDispatcher("hr-home.jsp"); // Adjust this as needed
	        rd.forward(req, res);            
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        // Optionally set an error attribute and forward to an error page
	        req.setAttribute("errorMessage", "Database error occurred.");
	        rd = req.getRequestDispatcher("hr-home.jsp"); // Adjust this as needed
	        rd.forward(req, res);
	    }
	}
}
