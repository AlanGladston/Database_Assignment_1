package java_emp_mgt.com.litmus7.employeemanager.dto;

import java.sql.Date;

public class Employee {
	    // Fields
	    private int employeeId;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phone;
	    private String department;
	    private double salary;
	    private Date joinDate;

	    // Default constructor
	    public Employee() {}

	    // Parameterized constructor
	    public Employee(int employeeId, String firstName, String lastName, String email,
	                    String phone, String department, double salary, Date joinDate) {
	        this.employeeId = employeeId;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.phone = phone;
	        this.department = department;
	        this.salary = salary;
	        this.joinDate = joinDate;
	    }

	    // Getters and Setters
	    public int getEmployeeId() { return employeeId; }
	    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

	    public String getFirstName() { return firstName; }
	    public void setFirstName(String firstName) { this.firstName = firstName; }

	    public String getLastName() { return lastName; }
	    public void setLastName(String lastName) { this.lastName = lastName; }

	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

	    public String getPhone() { return phone; }
	    public void setPhone(String phone) { this.phone = phone; }

	    public String getDepartment() { return department; }
	    public void setDepartment(String department) { this.department = department; }

	    public double getSalary() { return salary; }
	    public void setSalary(double salary) { this.salary = salary; }

	    public Date getJoinDate() { return joinDate; }
	    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }

	    // toString() for debugging/logging
	    @Override
	    public String toString() {
	        return "Employee{" +
	                "employeeId=" + employeeId +
	                ", firstName='" + firstName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", email='" + email + '\'' +
	                ", phone='" + phone + '\'' +
	                ", department='" + department + '\'' +
	                ", salary=" + salary +
	                ", joinDate=" + joinDate +
	                '}';
	    }
	}	


