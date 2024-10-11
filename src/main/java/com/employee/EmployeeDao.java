package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbconnection.connectionprovider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EmployeeDao{
	String first_name = null;
	int manager_id;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        first_name = (String) session.getAttribute("first_name");
        if (first_name == null || first_name.isEmpty()) {
            session.setAttribute("WarningMessage", "Error: First name not found in session.");
            return;
        }
        
        manager_id = (Integer) session.getAttribute("manager_id");
    	if (manager_id == 0) {
            session.setAttribute("WarningMessage", "Error: Manager ID not found in session.");
            return;
        }
        
        List<Employee> employees = getEmployees(first_name);
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("login.jsp").forward(req, res);
    }
	
	// HR Employee table
	public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        try(Connection con = connectionprovider.connectionp()) {
        	String query = "SELECT * FROM employees";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setEmployeeType(resultSet.getString("employee_type"));
                employee.setEmployeeDomain(resultSet.getString("employee_domain"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPassword(resultSet.getString("password"));
                employee.setMobile(resultSet.getString("mobile"));
                employee.setAddress(resultSet.getString("address"));
                employee.setManagerId(resultSet.getInt("manager_id"));

                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
	
	// Lead + Manager Employee table
	public List<Employee> getEmployeesByEmployeeId(int employee_id) {
	    List<Employee> employees = new ArrayList<>();
	    String query = "SELECT * FROM employees WHERE manager_id = ?";

	    try (Connection con = connectionprovider.connectionp();
	         PreparedStatement ps = con.prepareStatement(query)) {
	         ps.setInt(1, employee_id);
	         ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Employee employee = new Employee();
	            employee.setId(rs.getInt("employee_id"));
	            employee.setFirstName(rs.getString("first_name"));
	            employee.setLastName(rs.getString("last_name"));
	            employee.setEmployeeDomain(rs.getString("employee_domain"));
	            employee.setEmail(rs.getString("email"));
	            employee.setMobile(rs.getString("mobile"));
	            employee.setAddress(rs.getString("address"));
	            employees.add(employee);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return employees;
	}


	// Employee profile
	public List<Employee> getEmployees(String email) {
        List<Employee> employees = new ArrayList<>();
        
        try (Connection con = connectionprovider.connectionp()) {
            String query = "SELECT * FROM employees WHERE email = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Employee employee = new Employee();
                        employee.setId(rs.getInt("employee_id"));
                        employee.setFirstName(rs.getString("first_name"));
                        employee.setLastName(rs.getString("last_name"));
                        employee.setGender(rs.getString("gender"));
                        employee.setEmployeeType(rs.getString("employee_type"));
                        employee.setEmployeeDomain(rs.getString("employee_domain"));
                        employee.setEmail(rs.getString("email"));
                        employee.setMobile(rs.getString("mobile"));
                        employee.setAddress(rs.getString("address"));
                        employee.setManagerId(rs.getInt("manager_id"));
                        employees.add(employee);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return employees;
    }
}
