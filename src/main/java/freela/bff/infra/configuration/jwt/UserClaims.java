package freela.bff.infra.configuration.jwt;

public class UserClaims {
    private String email;
    private Integer userId;

    public UserClaims(String email, Integer userId) {
        this.email = email;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public Integer getUserId() {
        return userId;
    }
}
