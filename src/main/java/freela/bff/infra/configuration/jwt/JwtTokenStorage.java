package freela.bff.infra.configuration.jwt;

public class JwtTokenStorage {
    private static final ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    public static String getToken() {
        return tokenThreadLocal.get();
    }

    public static void setToken(String token) {
        tokenThreadLocal.set(token);
    }

    public static void clearToken() {
        tokenThreadLocal.remove();
    }
}

