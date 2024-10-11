package com.taskmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbconnection.connectionprovider;


public class taskManagerDao {

	public  List<taskManager> getAllResourceTask(int employee_id){
		List<taskManager> allTask = new ArrayList<>();

		try(Connection con = connectionprovider.connectionp()){
			String query = "SELECT * FROM task_manager tm JOIN employees e ON tm.assigned_to = e.employee_id WHERE e.employee_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, employee_id);
			ResultSet rs = ps.executeQuery();


			while(rs.next()) {
				taskManager tm = new taskManager();
				tm.setTask_id(rs.getInt("task_id"));
				tm.setTask_name(rs.getString("task_name"));
				tm.setAssigned_to(rs.getInt("assigned_to"));
				tm.setStatus(rs.getString("status"));
				tm.setDue_date(rs.getString("due_date"));
				tm.setStart_date(rs.getString("start_date"));
				tm.setEnd_date(rs.getString("end_date"));
				tm.setPriority(rs.getString("priority"));

				allTask.add(tm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allTask;

	}


	public  List<taskManager> getAllTask(int employee_id){ //Lead + Manager tasks can be obtained using this method
		List<taskManager> allTask = new ArrayList<>();

		try(Connection con = connectionprovider.connectionp()){
			String query = "SELECT tm.* FROM task_manager tm INNER JOIN employees e ON tm.assigned_to = e.employee_id WHERE tm.assigned_to = ? OR e.manager_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, employee_id);
			ps.setInt(2, employee_id);
			ResultSet rs = ps.executeQuery();


			while(rs.next()) {
				taskManager tm = new taskManager();
				tm.setTask_id(rs.getInt("task_id"));
				tm.setTask_name(rs.getString("task_name"));
				tm.setAssigned_to(rs.getInt("assigned_to"));
				tm.setStatus(rs.getString("status"));
				tm.setDue_date(rs.getString("due_date"));
				tm.setStart_date(rs.getString("start_date"));
				tm.setEnd_date(rs.getString("end_date"));
				tm.setPriority(rs.getString("priority"));

				allTask.add(tm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allTask;

	}
}
