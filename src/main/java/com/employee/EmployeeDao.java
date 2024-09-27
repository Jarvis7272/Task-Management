package com.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dbconnection.connectionprovider;

public class EmployeeDao{
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
}
