package java_emp_mgt.com.litmus7.employeemanager.ui;

import java.util.List;
import java_emp_mgt.com.litmus7.employeemanager.controller.EmployeeController;
import java_emp_mgt.com.litmus7.employeemanager.dto.Employee;
import java_emp_mgt.com.litmus7.employeemanager.dto.Response;
import java_emp_mgt.com.litmus7.employeemanager.util.CsvReader;

public class MainApp {

    public static void main(String[] args) {
        EmployeeController controller = new EmployeeController();
        String csvFilePath = "/java_emp_mgt/resources/employees.csv";

        //  Batch insert from CSV 
        try {
            List<String[]> csvRecords = CsvReader.readCSV(csvFilePath);
            Response<List<Employee>> batchResponse = controller.addEmployeesFromCSV(csvRecords);

            if (batchResponse.getStatusCode() == 200) {
                System.out.println("Batch insert successful. Inserted employees:");
                for (Employee emp : batchResponse.getData()) {
                    System.out.println(emp);
                }
            } else {
                System.out.println("Error code: " + batchResponse.getErrorCode() + " Message: " + batchResponse.getErrorMessage());
            }
        } catch (Exception e) {
            System.out.println("Failed to read CSV: " + e.getMessage());
        }

        //  Add single employee
        Employee newEmp = new Employee();
        newEmp.setEmployeeId(108);
        newEmp.setFirstName("Vaibhav");
        newEmp.setLastName("Narayanan");
        newEmp.setEmail("vaibhav@example.com");
        newEmp.setPhone("7356302341");
        newEmp.setDepartment("Engineering");
        newEmp.setSalary(40000);
        newEmp.setJoinDate(java.sql.Date.valueOf("2021-06-13"));

        Response<Employee> addResponse = controller.addEmployee(newEmp);
        if (addResponse.getStatusCode() == 200) {
            System.out.println("Employee added successfully: " + addResponse.getData());
        } else {
            System.out.println("Error code: " + addResponse.getErrorCode() + " Message: " + addResponse.getErrorMessage());
        }

        //  Get all employees 
        Response<List<Employee>> allEmpResponse = controller.getAllEmployees();
        if (allEmpResponse.getStatusCode() == 200) {
            System.out.println("All employees:");
            for (Employee emp : allEmpResponse.getData()) {
                System.out.println(emp);
            }
        } else {
            System.out.println("Error code: " + allEmpResponse.getErrorCode() + " Message: " + allEmpResponse.getErrorMessage());
        }

        // Get employee by ID
        int empIdToFetch = 101;
        Response<Employee> empResponse = controller.getEmployeeById(empIdToFetch);
        if (empResponse.getStatusCode() == 200) {
            System.out.println("Employee fetched: " + empResponse.getData());
        } else {
            System.out.println("Error code: " + empResponse.getErrorCode() + " Message: " + empResponse.getErrorMessage());
        }

        //  Update employee 
        Employee updateEmp = new Employee();
        updateEmp.setEmployeeId(101);
        updateEmp.setFirstName("Akshay");
        updateEmp.setLastName("John");
        updateEmp.setEmail("akshayjohn@example.com");
        updateEmp.setPhone("8129457890");
        updateEmp.setDepartment("Engineering");
        updateEmp.setSalary(75000);
        updateEmp.setJoinDate(java.sql.Date.valueOf("2021-06-15"));

        Response<Employee> updateResponse = controller.updateEmployee(updateEmp);
        if (updateResponse.getStatusCode() == 200) {
            System.out.println("Employee updated successfully: " + updateResponse.getData());
        } else {
            System.out.println("Error code: " + updateResponse.getErrorCode() + " Message: " + updateResponse.getErrorMessage());
        }

        // Delete employee 
        int empIdToDelete = 101;
        Response<Employee> deleteResponse = controller.deleteEmployee(empIdToDelete);
        if (deleteResponse.getStatusCode() == 200) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Error code: " + deleteResponse.getErrorCode() + " Message: " + deleteResponse.getErrorMessage());
        }
    }
}
