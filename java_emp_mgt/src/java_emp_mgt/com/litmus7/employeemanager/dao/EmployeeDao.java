package java_emp_mgt.com.litmus7.employeemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java_emp_mgt.com.litmus7.employeemanager.dto.Employee;
import java_emp_mgt.com.litmus7.employeemanager.exception.EmployeeDaoException;
import java_emp_mgt.com.litmus7.employeemanager.util.DBConnection;

public class EmployeeDao {

    // Utility: Map ResultSet to Employee object
    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setEmployeeId(rs.getInt("emp_id"));
        emp.setFirstName(rs.getString("first_name"));
        emp.setLastName(rs.getString("last_name"));
        emp.setEmail(rs.getString("email"));
        emp.setPhone(rs.getString("phone"));
        emp.setDepartment(rs.getString("department"));
        emp.setSalary(rs.getDouble("salary"));
        emp.setJoinDate(rs.getDate("join_date"));
        return emp;
    }

    // Save a single employee
    public void saveEmployee(Employee emp) {
        String sql = "INSERT INTO employee(emp_id, first_name, last_name, email, phone, department, salary, join_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, emp.getEmployeeId());
            ps.setString(2, emp.getFirstName());
            ps.setString(3, emp.getLastName());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setString(6, emp.getDepartment());
            ps.setDouble(7, emp.getSalary());
            ps.setDate(8, emp.getJoinDate());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new EmployeeDaoException(
                    "DAO-INSERT-ERROR: Failed to insert employee with ID " + emp.getEmployeeId(), e);
        }
    }

    // Get employee by ID
    public Employee getEmployeeById(int empId) {
        String sql = "SELECT * FROM employee WHERE emp_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new EmployeeDaoException(
                    "DAO-FETCH-ERROR: Failed to fetch employee with ID " + empId, e);
        }
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
            return employees;

        } catch (SQLException e) {
            throw new EmployeeDaoException("DAO-FETCH-ALL-ERROR: Failed to fetch employees", e);
        }
    }

    // Update employee
    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET first_name=?, last_name=?, email=?, phone=?, department=?, salary=?, join_date=? " +
                     "WHERE emp_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setString(5, emp.getDepartment());
            ps.setDouble(6, emp.getSalary());
            ps.setDate(7, emp.getJoinDate());
            ps.setInt(8, emp.getEmployeeId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new EmployeeDaoException(
                    "DAO-UPDATE-ERROR: Failed to update employee with ID " + emp.getEmployeeId(), e);
        }
    }

    // Delete employee
    public boolean deleteEmployee(int empId) {
        String sql = "DELETE FROM employee WHERE emp_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new EmployeeDaoException(
                    "DAO-DELETE-ERROR: Failed to delete employee with ID " + empId, e);
        }
    }

    // Batch insert employees
    public int[] addEmployeesInBatch(List<Employee> employeeList) {
        String sql = "INSERT INTO employee(emp_id, first_name, last_name, email, phone, department, salary, join_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Employee emp : employeeList) {
                ps.setInt(1, emp.getEmployeeId());
                ps.setString(2, emp.getFirstName());
                ps.setString(3, emp.getLastName());
                ps.setString(4, emp.getEmail());
                ps.setString(5, emp.getPhone());
                ps.setString(6, emp.getDepartment());
                ps.setDouble(7, emp.getSalary());
                ps.setDate(8, emp.getJoinDate());
                ps.addBatch();
            }
            return ps.executeBatch();

        } catch (SQLException e) {
            throw new EmployeeDaoException("DAO-BATCH-INSERT-ERROR: Failed to batch insert employees", e);
        }
    }

    // Transactional transfer of employees to new department
    public List<Integer> transferEmployeesToDepartment(List<Integer> employeeIds, String newDepartment) {
        String sql = "UPDATE employee SET department=? WHERE emp_id=?";
        List<Integer> updatedIds = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false); // Start transaction

            for (int id : employeeIds) {
                ps.setString(1, newDepartment);
                ps.setInt(2, id);
                int affected = ps.executeUpdate();
                if (affected > 0) updatedIds.add(id);
            }

            conn.commit(); // Commit all changes
            return updatedIds;

        } catch (SQLException e) {
            try {
                DBConnection.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new EmployeeDaoException(
                    "DAO-TRANSFER-ERROR: Failed to transfer employees to department " + newDepartment, e);
        }
    }
}
