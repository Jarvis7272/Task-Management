package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update")
public class EmployeeUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String gender = req.getParameter("gender");
        String employeeType = req.getParameter("employeeType");
        String employeeDomain = req.getParameter("employeeDomain");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String mobile = req.getParameter("mobile");
        String address = req.getParameter("address");
        String manager_id = req.getParameter("manager_id");

        HttpSession session = req.getSession();
        StringBuilder query = new StringBuilder("UPDATE employees SET ");
        boolean firstField = true; // Flag to manage comma placement

        try (Connection con = com.dbconnection.connectionprovider.connectionp()) {
        	if (firstName != null && !firstName.isEmpty()) {
        	    query.append("first_name = ?");
        	    firstField = false;
        	}
        	if (lastName != null && !lastName.isEmpty()) {
        	    if (!firstField) query.append(", ");
        	    query.append("last_name = ?");    
        	    firstField = false; // Update flag since we are adding a field
        	}
        	if (gender != null && !gender.isEmpty()) {
        	    if (!firstField) query.append(", ");
        	    query.append("gender = ?");    
        	    firstField = false; // Update flag since we are adding a field
        	}
        	if (employeeType != null && !employeeType.isEmpty()) {
        	    if (!firstField) query.append(", ");
        	    query.append("employee_type = ?");    
        	    firstField = false; // Update flag since we are adding a field
        	}
        	if (employeeDomain != null && !employeeDomain.isEmpty()) {
        	    if (!firstField) query.append(", ");
        	    query.append("employee_domain = ?"); 
        	    firstField = false; // Update flag since we are adding a field
        	}
        	if (email != null && !email.isEmpty()) {
        	    if (!firstField) query.append(", ");
        	    query.append("email = ?");   
        	    firstField = false; // Update flag since we are adding a field
        	}
        	if (password != null && !password.isEmpty()) {
        	    if (!firstField) query.append(", ");
        	    query.append("password = ?"); 
        	    firstField = false; // Update flag since we are adding a field
        	}
        	if (mobile != null && !mobile.isEmpty()) {
        	    if (!firstField) query.append(", ");
        	    query.append("mobile = ?"); 
        	    firstField = false; // Update flag since we are adding a field
        	}
        	if (address != null && !address.isEmpty()) {
        	    if (!firstField) query.append(", ");
        	    query.append("address = ?"); 
        	    firstField = false; // Update flag since we are adding a field
        	}

        	// Handle manager_id
        	if (manager_id != null && !manager_id.isEmpty()) {
        	    if (!firstField) query.append(", ");
        	    query.append("manager_id = ?");
        	    
        	} else {
        	    if (!firstField) query.append(", ");
        	    query.append("manager_id = NULL"); // Explicitly handle NULL
        	}

        	// Always include the WHERE clause
        	query.append(" WHERE employee_id = ?");

            try (PreparedStatement ps = con.prepareStatement(query.toString())) {
                int index = 1;

                // Set parameters based on the query built
                if (firstName != null && !firstName.isEmpty()) ps.setString(index++, firstName);
                if (lastName != null && !lastName.isEmpty()) ps.setString(index++, lastName);
                if (gender != null && !gender.isEmpty()) ps.setString(index++, gender);
                if (employeeType != null && !employeeType.isEmpty()) ps.setString(index++, employeeType);
                if (employeeDomain != null && !employeeDomain.isEmpty()) ps.setString(index++, employeeDomain);
                if (email != null && !email.isEmpty()) ps.setString(index++, email);
                if (password != null && !password.isEmpty()) ps.setString(index++, password);
                if (mobile != null && !mobile.isEmpty()) ps.setString(index++, mobile);
                if (address != null && !address.isEmpty()) ps.setString(index++, address);

                // Handle manager_id
                if (manager_id != null && !manager_id.isEmpty()) {
                    ps.setInt(index++, Integer.parseInt(manager_id));
                }

                ps.setInt(index, id);

                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    session.setAttribute("SuccessMessage", "Employee updated successfully. Employee ID: " + id);
                } else {
                    session.setAttribute("WarningMessage", "Error updating employee! Please try again.");
                }

                res.sendRedirect("hr-home.jsp");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            req.setAttribute("errorMessage", "Database error occurred.");
            res.sendRedirect("hr-home.jsp");
        }
    }
}
