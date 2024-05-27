package request;

public class RegisterRequest extends ParentRequest {

    private final String username;
    private final String password;
    private final String email;

    public RegisterRequest(String name, String password, String email) {
        super();
        this.username = name;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
