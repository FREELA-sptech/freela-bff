package freela.bff.infra.configuration.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import freela.bff.domain.model.response.core.ErrorResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JwtConfiguration jwtConfiguration;

    public JwtAuthorizationFilter(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = extractToken(request);

            if (token != null && jwtConfiguration.validateToken(token)) {
                Authentication authentication = buildAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                JwtTokenStorage.setToken(token);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ErrorResponse errorResponse = new ErrorResponse("Token inválido ou expirado");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(errorResponse);

            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }

    private Authentication buildAuthentication(String token) {
        // Extract user information from the token (e.g., user ID, roles)
        // Create an instance of UsernamePasswordAuthenticationToken with the user's details
        return new UsernamePasswordAuthenticationToken("userId", null, null);
    }
}
