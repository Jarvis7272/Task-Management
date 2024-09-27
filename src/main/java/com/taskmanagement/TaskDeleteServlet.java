package com.taskmanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbconnection.connectionprovider;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteTask")
public class TaskDeleteServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd;
		int task_id = Integer.parseInt(req.getParameter("task_id"));
		try(Connection con = connectionprovider.connectionp();){
			String query = "Delete from task_manager where task_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, task_id);
			ps.executeUpdate();
			rd = req.getRequestDispatcher("lead-home.jsp");
			rd.forward(req, res);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
