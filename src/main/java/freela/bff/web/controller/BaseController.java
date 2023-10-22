package freela.bff.web.controller;


import freela.bff.infra.configuration.jwt.JwtConfiguration;
import freela.bff.infra.configuration.jwt.JwtTokenStorage;
import freela.bff.infra.configuration.jwt.UserClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    private final JwtConfiguration jwtConfiguration;

    @Autowired
    public BaseController(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    protected UserClaims getUserClaims() {
        String token = JwtTokenStorage.getToken();
        return jwtConfiguration.getClaimsFromToken(token);
    }
}
