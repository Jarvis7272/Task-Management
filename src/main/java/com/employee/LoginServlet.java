package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        try(Connection con = com.dbconnection.connectionprovider.connectionp()){
            String query = "SELECT * FROM employees WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            HttpSession session = req.getSession();
            if(rs.next()) {
                int employee_id = rs.getInt("employee_id");
                String employee_type = rs.getString("employee_type");
                int manager_id = rs.getInt("manager_id");
                String first_name = rs.getString("first_name");

                session.setAttribute("email", email);
                session.setAttribute("password", password);
                session.setAttribute("employee_id", employee_id);
                session.setAttribute("manager_id", manager_id);
                session.setAttribute("employee_type", employee_type);
                session.setAttribute("first_name", first_name);

                res.sendRedirect("home");

            } else {
            	showLoginFailedAlert(res);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void showLoginFailedAlert(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(
            "<!DOCTYPE html>" +
            "<html lang='en'>" +
            "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<title>Login Failed</title>" +
                // SweetAlert v1 CDN
                "<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>" +
            "</head>" +
            "<body>" +
                "<script>" +
                    "swal('Login Failed!', 'Incorrect username or password.', 'warning')" +
                    ".then(() => {" +
                        "window.location = 'login.jsp';" +  // Redirect back to login page
                    "});" +
                "</script>" +
            "</body>" +
            "</html>"
        );
    }
}
