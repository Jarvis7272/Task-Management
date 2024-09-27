package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        Connection con = null;
        RequestDispatcher rd = null;
        
        try {
            con = com.dbconnection.connectionprovider.connectionp();
            String query = "SELECT * FROM employees WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            HttpSession session = req.getSession();
            if(rs.next()) {
                int employee_id = rs.getInt("employee_id");
                String employee_type = rs.getString("employee_type");

                session.setAttribute("email", email);
                session.setAttribute("password", password);
                session.setAttribute("employee_id", employee_id);

                if("hr".equalsIgnoreCase(employee_type)) {
                    rd = req.getRequestDispatcher("hr-home.jsp");
                } else if("resource".equalsIgnoreCase(employee_type)) {
                    rd = req.getRequestDispatcher("resource-home.jsp");
                } else if("lead".equalsIgnoreCase(employee_type)) {
                    rd = req.getRequestDispatcher("lead-home.jsp");
                } else if("manager".equalsIgnoreCase(employee_type)) {
                    rd = req.getRequestDispatcher("manager-home.jsp");
                }

                rd.forward(req, res);
                
            } else {
                req.setAttribute("error", "Invalid email or password");
                rd = req.getRequestDispatcher("login.jsp");
                rd.forward(req, res);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
