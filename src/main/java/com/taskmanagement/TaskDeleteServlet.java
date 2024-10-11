package com.taskmanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.dbconnection.connectionprovider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteTask")
public class TaskDeleteServlet extends HttpServlet{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		int task_id = Integer.parseInt(req.getParameter("task_id"));
		try(Connection con = connectionprovider.connectionp();){
			String query = "Delete from task_manager where task_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, task_id);
			int affectedRows = ps.executeUpdate();
			// Retrieve the generated employee ID
			if (affectedRows > 0) {
				session.setAttribute("SuccessMessage", "Task deleted successfully. Task ID: "+task_id);
			} else {
				session.setAttribute("WarningMessage", "Error adding task! Please try again.");
			}

			
			String employee_type = (String) session.getAttribute("employee_type");
			if("manager".equalsIgnoreCase(employee_type)) {
                res.sendRedirect("manager-home.jsp");
            } else if("resource".equalsIgnoreCase(employee_type)) {
            	res.sendRedirect("resource-home.jsp");
            } else if("lead".equalsIgnoreCase(employee_type)) {
            	res.sendRedirect("lead-home.jsp");
            }


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute("DangerMessage", "Database error occurred.");
			
			String employee_type = (String) session.getAttribute("employee_type");
			if("manager".equalsIgnoreCase(employee_type)) {
                res.sendRedirect("manager-home.jsp");
            } else if("resource".equalsIgnoreCase(employee_type)) {
            	res.sendRedirect("resource-home.jsp");
            } else if("lead".equalsIgnoreCase(employee_type)) {
            	res.sendRedirect("lead-home.jsp");
            }
		}
	}





}
