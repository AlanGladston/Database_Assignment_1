package java_emp_mgt.com.litmus7.employeemanager.exception;

public class CsvFileAccessException extends Exception {
    public CsvFileAccessException(String message) {
        super(message);
    }

    public CsvFileAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
