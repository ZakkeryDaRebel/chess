package request;

public class RegisterRequest extends ParentRequest {

    private final String name;
    private final String password;
    private final String email;

    public RegisterRequest(String name, String password, String email) {
        super();
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
