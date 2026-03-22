package in.careerit.main.auth;

public class LoginResponse {

    private Long userId;
    private String username;
    private String role;

    public LoginResponse(Long userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}