package result;

public class ParentResult {
    private Boolean success;
    private String message;

    public ParentResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void nullParentVariables() {
        success = null;
        message = null;
    }

    public void nullSuccess() {
        success = null;
    }
}
