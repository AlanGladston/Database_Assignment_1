package java_emp_mgt.com.litmus7.employeemanager.util;

import java.sql.Date;
import java.util.regex.Pattern;

import java_emp_mgt.com.litmus7.employeemanager.dto.Employee;

public class Validation {

    // Regex patterns
    private static final String NAME_PATTERN = "^[A-Za-z\\s'-]{2,30}$"; // Letters, spaces, hyphens, 2-30 chars
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_PATTERN = "^[0-9]{10}$"; // 10 digits

    
    // Validate first name or last name
    public static boolean isValidName(String name) {
        if (name == null) return false;
        return Pattern.matches(NAME_PATTERN, name);
    }

    // Validate email
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    // Validate phone number
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return Pattern.matches(PHONE_PATTERN, phone);
    }

    // Validate salary (positive value)
    public static boolean isValidSalary(double salary) {
        return salary > 0;
    }

   

    // Validate join date (format: yyyy-MM-dd)
    public static boolean isValidDate(Date joinDate) {
        return joinDate != null; // java.sql.Date ensures valid format already
    }

    // Optional: validate entire employee object
    public static boolean isValidEmployee(Employee emp) {
        return emp != null &&
                isValidName(emp.getFirstName()) &&
                isValidName(emp.getLastName()) &&
                isValidEmail(emp.getEmail()) &&
                isValidPhone(emp.getPhone()) &&
                isValidSalary(emp.getSalary()) &&
                isValidDate(emp.getJoinDate());
    }
}
