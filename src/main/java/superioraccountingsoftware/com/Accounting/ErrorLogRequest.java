package superioraccountingsoftware.com.Accounting;

public class ErrorLogRequest {
    private String errorMessage;
    private String userId;    // Optional: The ID of the user that encountered the error
    private String context;   // Optional: Additional context information about the error

    // Getters and Setters
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
