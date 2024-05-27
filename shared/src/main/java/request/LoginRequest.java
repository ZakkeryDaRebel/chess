package request;

public class LoginRequest extends ParentRequest {

    private final String username;
    private final String password;

    public LoginRequest(String name, String password) {
        super();
        this.username = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
