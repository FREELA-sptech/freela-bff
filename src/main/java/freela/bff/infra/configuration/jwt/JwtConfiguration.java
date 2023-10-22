package freela.bff.infra.configuration.jwt;

import freela.bff.domain.model.response.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtConfiguration {

    @Getter
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.validity}")
    private long jwtTokenValidity;

    public String generateJwtToken(User user) {
        Claims claims = Jwts.claims();
        claims.setSubject(user.getEmail());
        claims.put("userId", user.getId());

        Date expirationDate = new Date(System.currentTimeMillis() + jwtTokenValidity * 1000);

        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(expirationDate)
                .compact();

        return jwtToken;
    }


    public UserClaims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject();
        Integer userId = claims.get("userId", Integer.class);

        return new UserClaims(email, userId);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
