package request;

public class LoginRequest extends ParentRequest {

    private final String name;
    private final String password;

    public LoginRequest(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
