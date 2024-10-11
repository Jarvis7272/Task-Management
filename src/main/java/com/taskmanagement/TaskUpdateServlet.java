package com.taskmanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.dbconnection.connectionprovider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateTask")
public class TaskUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int task_id = Integer.parseInt(req.getParameter("task_id"));
        String status = req.getParameter("status");
        String start_date = req.getParameter("start_date");
        String end_date = req.getParameter("end_date");

        HttpSession session = req.getSession();
        Integer employee_id = (Integer) session.getAttribute("employee_id");
        
        taskManagerDao tasks = new taskManagerDao();
        List<taskManager> taskList = tasks.getAllResourceTask(employee_id); // Get all tasks for this individual employee
        List<taskManager> taskManager = tasks.getAllTask(employee_id);  // Get all tasks for this employee and its sub-ordinates
        boolean canUpdate = false;

        for (taskManager task : taskList) {
            if (task.getTask_id() == task_id) {
                canUpdate = true;
                break;
            }
        }
        
        for (taskManager task : taskManager) {
            if (task.getTask_id() == task_id) {
                canUpdate = true;
                break;
            }
        }

        if (!canUpdate) {
            session.setAttribute("WarningMessage", "You are not authorized to update this task.");
            res.sendRedirect("home");
            return;
        }
             
        
        StringBuilder query = new StringBuilder("UPDATE task_manager SET");
        boolean firstCondition = true;

        if (status != null && !status.isEmpty()) {
            query.append(" status = ?");
            firstCondition = false;
        }
        if (start_date != null && !start_date.isEmpty()) {
            if (!firstCondition) query.append(",");
            query.append(" start_date = ?");
            firstCondition = false;
        }
        if (end_date != null && !end_date.isEmpty()) {
            if (!firstCondition) query.append(",");
            query.append(" end_date = ?");
        }

        query.append(" WHERE task_id = ?");

        try (Connection con = connectionprovider.connectionp();
             PreparedStatement ps = con.prepareStatement(query.toString())) {

            int index = 1;

            if (status != null && !status.isEmpty()) {
                ps.setString(index++, status);
            }
            if (start_date != null && !start_date.isEmpty()) {
                java.sql.Date startDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(start_date).getTime());
                ps.setDate(index++, startDate);
            }
            if (end_date != null && !end_date.isEmpty()) {
                java.sql.Date endDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(end_date).getTime());
                ps.setDate(index++, endDate);
            }

            ps.setInt(index, task_id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                session.setAttribute("SuccessMessage", "Task updated successfully. Task ID: " + task_id);
                res.sendRedirect("home");
            } else {
                session.setAttribute("WarningMessage", "Error updating task! Please try again.");
                res.sendRedirect("home");
            }


        } catch (ParseException e) {
            e.printStackTrace();
            session.setAttribute("DangerMessage", "Date parsing error occurred.");
            res.sendRedirect("home");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("DangerMessage", "Database error occurred.");
            res.sendRedirect("home");
        }
    }
}
