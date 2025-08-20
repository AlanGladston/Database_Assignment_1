package java_emp_mgt.com.litmus7.employeemanager.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import java_emp_mgt.com.litmus7.employeemanager.dao.EmployeeDao;
import java_emp_mgt.com.litmus7.employeemanager.dto.Employee;
import java_emp_mgt.com.litmus7.employeemanager.exception.EmployeeServiceException;
import java_emp_mgt.com.litmus7.employeemanager.util.Validation;

public class EmployeeService {

    private final EmployeeDao dao = new EmployeeDao();

    public boolean addEmployee(Employee emp) throws EmployeeServiceException {
        if (!Validation.isValidEmployee(emp)) throw new EmployeeServiceException("Invalid employee data");
        try {
            if (dao.getEmployeeById(emp.getEmployeeId()) != null) 
                throw new EmployeeServiceException("Employee already exists with ID " + emp.getEmployeeId());
            dao.saveEmployee(emp);
            return true;
        } catch (Exception e) {
            throw new EmployeeServiceException("Failed to add employee", e);
        }
    }

    public List<Employee> addEmployeesFromCSV(List<String[]> csvRecords) throws EmployeeServiceException {
        List<Employee> validEmployees = new ArrayList<>();
        for (int i = 0; i < csvRecords.size(); i++) {
            String[] row = csvRecords.get(i);
            try {
                Employee emp = new Employee();
                emp.setEmployeeId(Integer.parseInt(row[0].trim()));
                emp.setFirstName(row[1].trim());
                emp.setLastName(row[2].trim());
                emp.setEmail(row[3].trim());
                emp.setPhone(row[4].trim());
                emp.setDepartment(row[5].trim());
                emp.setSalary(Double.parseDouble(row[6].trim()));
                emp.setJoinDate(Date.valueOf(row[7].trim()));
                if (!Validation.isValidEmployee(emp)) continue;
                validEmployees.add(emp);
            } catch (Exception ignored) {}
        }
        if (validEmployees.isEmpty()) throw new EmployeeServiceException("No valid employees in CSV");
        try {
            dao.addEmployeesInBatch(validEmployees);
            return validEmployees;
        } catch (Exception e) {
            throw new EmployeeServiceException("Failed batch insert", e);
        }
    }

    public Employee getEmployeeById(int empId) throws EmployeeServiceException {
        try {
            Employee emp = dao.getEmployeeById(empId);
            if (emp == null) throw new EmployeeServiceException("Employee not found with ID " + empId);
            return emp;
        } catch (Exception e) {
            throw new EmployeeServiceException("Failed to fetch employee", e);
        }
    }

    public List<Employee> getAllEmployees() throws EmployeeServiceException {
        try {
            return dao.getAllEmployees();
        } catch (Exception e) {
            throw new EmployeeServiceException("Failed to fetch all employees", e);
        }
    }

    public boolean updateEmployee(Employee emp) throws EmployeeServiceException {
        try {
            boolean updated = dao.updateEmployee(emp);
            if (!updated) throw new EmployeeServiceException("Employee not found for update: " + emp.getEmployeeId());
            return true;
        } catch (Exception e) {
            throw new EmployeeServiceException("Failed to update employee", e);
        }
    }

    public boolean deleteEmployee(int empId) throws EmployeeServiceException {
        try {
            boolean deleted = dao.deleteEmployee(empId);
            if (!deleted) throw new EmployeeServiceException("Employee not found for delete: " + empId);
            return true;
        } catch (Exception e) {
            throw new EmployeeServiceException("Failed to delete employee", e);
        }
    }
}
