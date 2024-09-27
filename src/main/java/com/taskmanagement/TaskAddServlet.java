package com.taskmanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbconnection.connectionprovider;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addTask")
public class TaskAddServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String task_name = req.getParameter("task_name");
		String assigned_to = req.getParameter("assigned_to");
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
        
        RequestDispatcher rd = null;
        try(Connection con = connectionprovider.connectionp()){
        	String query = "INSERT into task_manager(task_name, assigned_to, status, due_date, priority) values(?,?,?,?,?)";
        	PreparedStatement ps = con.prepareStatement(query);
        	
        	ps.setString(1, task_name);
        	ps.setString(2, assigned_to);
        	ps.setString(3, status);
        	ps.setDate(4, dueDate);
        	ps.setString(5, priority);
        	
        	ps.executeUpdate();
        	
        	rd = req.getRequestDispatcher("lead-home.jsp");
        	rd.forward(req, res);
        	
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	

}
