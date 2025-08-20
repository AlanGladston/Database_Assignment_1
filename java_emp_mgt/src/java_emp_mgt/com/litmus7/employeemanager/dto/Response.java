package java_emp_mgt.com.litmus7.employeemanager.dto;

public class Response<T> {
    private int statusCode;
    private String errorCode;  // optional, for failures
    private String errorMessage; // optional, or general message
    private T data;

    // Constructor for success with data and message
    public Response(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.errorMessage = message;
        this.data = data;
        this.errorCode = null;
    }

    // Constructor for failure with error code and message
    public Response(int statusCode, String message, String errorCode) {
        this.statusCode = statusCode;
        this.errorMessage = message;
        this.errorCode = errorCode;
        this.data = null;
    }

    // Getters and setters
    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
