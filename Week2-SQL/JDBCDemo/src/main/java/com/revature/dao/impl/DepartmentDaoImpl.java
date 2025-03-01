package com.revature.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DepartmentDao;
import com.revature.model.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public List<Department> getDepartments() {
		String sql = "select * from department";
		
		List<Department> departments = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double salary = rs.getDouble("monthly_budget");
				Department d = new Department(deptId, deptName, salary);
				departments.add(d);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
	
		String sql = "select * from department where dept_id = ?";
		Department d = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id); // jdbc 1 based index
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double salary = rs.getDouble("monthly_budget");
				d = new Department(deptId, deptName, salary);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return d;
	}

	@Override
	public int createDepartment(Department d) {
		int departmentsCreated = 0;
		String sql = "insert into department (dept_name, monthly_budget) values (?, ?)";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, d.getName());
			ps.setDouble(2, d.getMonthlyBudget());
			
			departmentsCreated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return departmentsCreated;
	}

	@Override
	public int updateDepartment(Department d) {
		int departmentsUpdated = 0;
		String sql = "update department "
				+ "set dept_name = ?, "
				+ "monthly_budget = ? "
				+ "where dept_id = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, d.getName());
			ps.setDouble(2, d.getMonthlyBudget());
			ps.setInt(3, d.getId());
			
			departmentsUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return departmentsUpdated;
	}

	@Override
	public int deleteDepartment(int id) {
		int rowsDeleted = 0;
		String sql = "delete from department where dept_id = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsDeleted;
	}

	@Override
	public Department createDepartmentWithFunction(Department d) {
		
		String sql = "select * from adddept(?, ?)";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, d.getName());
			ps.setDouble(2, d.getMonthlyBudget());
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double salary = rs.getDouble("monthly_budget");
				d = new Department(deptId, deptName, salary);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return d;
	}

	@Override
	public void increaseDepartmentBudgetWithFunction(Department d, double increase) {
		
		String sql = "{call increase_budget(?,?)}";
		
		try(Connection c = ConnectionUtil.getConnection();
			CallableStatement cs = c.prepareCall(sql)){
			
			cs.setDouble(1, increase);
			cs.setInt(2, d.getId());
			
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
