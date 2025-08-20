package java_emp_mgt.com.litmus7.employeemanager.exception;

public class EmployeeServiceException extends  RuntimeException {

    private int errorCode;
    
    public EmployeeServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmployeeServiceException(String message) {
        super(message);
    }
    
    public EmployeeServiceException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
