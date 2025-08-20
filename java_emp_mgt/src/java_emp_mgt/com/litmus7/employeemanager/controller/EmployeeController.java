package java_emp_mgt.com.litmus7.employeemanager.controller;

import java.util.List;
import java_emp_mgt.com.litmus7.employeemanager.dto.Employee;
import java_emp_mgt.com.litmus7.employeemanager.dto.Response;
import java_emp_mgt.com.litmus7.employeemanager.exception.EmployeeServiceException;
import java_emp_mgt.com.litmus7.employeemanager.service.EmployeeService;

public class EmployeeController {

    private final EmployeeService service = new EmployeeService();

    public Response<Employee> addEmployee(Employee emp) {
        try {
            service.addEmployee(emp);
            return new Response<>(200, "Employee added successfully", emp);
        } catch (EmployeeServiceException e) {
            int code = e.getMessage().contains("exists") ? 409 : 400;
            return new Response<>(code, e.getMessage(), "ERR01");
        } catch (Exception e) {
            return new Response<>(500, "Internal error", "ERR99");
        }
    }

    public Response<List<Employee>> addEmployeesFromCSV(List<String[]> csvRecords) {
        try {
            List<Employee> employees = service.addEmployeesFromCSV(csvRecords);
            return new Response<>(200, "Batch insert successful", employees);
        } catch (EmployeeServiceException e) {
            return new Response<>(400, e.getMessage(), "ERR02");
        } catch (Exception e) {
            return new Response<>(500, "Internal error", "ERR99");
        }
    }

    public Response<Employee> getEmployeeById(int empId) {
        try {
            Employee emp = service.getEmployeeById(empId);
            return new Response<>(200, "Employee fetched", emp);
        } catch (EmployeeServiceException e) {
            return new Response<>(404, e.getMessage(), "ERR03");
        } catch (Exception e) {
            return new Response<>(500, "Internal error", "ERR99");
        }
    }

    public Response<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = service.getAllEmployees();
            return new Response<>(200, "All employees fetched", employees);
        } catch (EmployeeServiceException e) {
            return new Response<>(500, e.getMessage(), "ERR04");
        }
    }

    public Response<Employee> updateEmployee(Employee emp) {
        try {
            service.updateEmployee(emp);
            return new Response<>(200, "Employee updated", emp);
        } catch (EmployeeServiceException e) {
            return new Response<>(404, e.getMessage(), "ERR05");
        } catch (Exception e) {
            return new Response<>(500, "Internal error", "ERR99");
        }
    }

    public Response<Employee> deleteEmployee(int empId) {
        try {
            service.deleteEmployee(empId);
            return new Response<>(200, "Employee deleted", null);
        } catch (EmployeeServiceException e) {
            return new Response<>(404, e.getMessage(), "ERR06");
        } catch (Exception e) {
            return new Response<>(500, "Internal error", "ERR99");
        }
    }
}
