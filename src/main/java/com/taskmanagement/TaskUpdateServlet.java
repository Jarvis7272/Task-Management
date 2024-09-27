package com.taskmanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.dbconnection.connectionprovider;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/updateTask")
public class TaskUpdateServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int task_id = Integer.parseInt(req.getParameter("task_id"));
		String status = req.getParameter("status");
		String start_date = req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		
		java.sql.Date startDate = null;
		java.sql.Date endDate = null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
		    java.util.Date parsedDate = dateFormat.parse(start_date);
		    startDate = new java.sql.Date(parsedDate.getTime());
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		if (end_date != null && !end_date.isEmpty()) {
		    try {
		        java.util.Date parsedDate = dateFormat.parse(end_date);
		        endDate = new java.sql.Date(parsedDate.getTime());
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		} else {
		    endDate = null;
		}
		
		RequestDispatcher rd = null;
        try(Connection con = connectionprovider.connectionp()){
        	String query = "UPDATE task_manager set status = ?, start_date = ?, end_date = ? WHERE task_id = ?";
        	PreparedStatement ps = con.prepareStatement(query);
        	
        	ps.setString(1, status);
        	ps.setDate(2, startDate);
        	ps.setDate(3, endDate);
        	ps.setInt(4, task_id);
        	
        	ps.executeUpdate();
        	
        	rd = req.getRequestDispatcher("lead-home.jsp");
        	rd.forward(req, res);
        	
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
