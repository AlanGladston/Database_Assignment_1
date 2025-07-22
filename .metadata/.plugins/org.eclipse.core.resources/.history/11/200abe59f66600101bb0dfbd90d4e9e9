package com.employeemanager.util;

import com.employeemanager.model.Employee;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PHONE_REGEX =
            Pattern.compile("^\\d{10}$");

    private static final Pattern DATE_REGEX =
            Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public static boolean validateEmployee(Employee emp) {
        return isNonEmpty(emp.getEmpId()) && isNumeric(emp.getEmpId())
            && isNonEmpty(emp.getFirstName())
            && isNonEmpty(emp.getLastName())
            && isValidEmail(emp.getEmail())
            && isValidPhone(emp.getPhone())
            && isNonEmpty(emp.getDepartment())
            && isValidSalary(emp.getSalary())
            && isValidDate(emp.getJoinDate());
    }

    public static boolean isNonEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static boolean isValidEmail(String email) {
        return isNonEmpty(email) && EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return isNonEmpty(phone) && PHONE_REGEX.matcher(phone).matches();
    }

    public static boolean isValidSalary(String salary) {
        return isNonEmpty(salary) && salary.matches("\\d+(\\.\\d{1,2})?");
    }

    public static boolean isValidDate(String date) {
        return isNonEmpty(date) && DATE_REGEX.matcher(date).matches();
    }
}
