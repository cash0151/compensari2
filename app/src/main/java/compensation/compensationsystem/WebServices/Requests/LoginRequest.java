package compensation.compensationsystem.WebServices.Requests;

/**
 * Created by Irina.B on 20.10.2016.
 */
public class LoginRequest
{
    private String Token, User, Password;

    public LoginRequest(String user, String password, String token)
    {
        User = user;
        Password = password;
        Token = token;
    }

    public String getPassword() {
        return Password;
    }

    public String getToken() {
        return Token;
    }

    public String getUser() {
        return User;
    }
}
