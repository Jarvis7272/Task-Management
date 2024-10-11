package com.home;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/home")
public class HomeRedirect extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		
		String employee_type = (String) session.getAttribute("employee_type");
		
		if (employee_type == null) {
	        res.sendRedirect("login.jsp");
	        return;
	    }
		
		if("hr".equalsIgnoreCase(employee_type)) {
            res.sendRedirect("hr-home.jsp");
        } else if("resource".equalsIgnoreCase(employee_type)) {
        	res.sendRedirect("resource-home.jsp");
        } else if("lead".equalsIgnoreCase(employee_type)) {
        	res.sendRedirect("lead-home.jsp");
        } else if("manager".equalsIgnoreCase(employee_type)) {
        	res.sendRedirect("manager-home.jsp");
        }else {
        	res.sendRedirect("login.jsp");
        }
	}
}
