package freela.bff.infra.repository;

import freela.bff.domain.model.request.user.RegisterRequest;
import freela.bff.infra.repository.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import freela.bff.domain.model.response.user.User;

@Repository
public class UserRepository implements IUserRepository {
    private final String baseURL = "http://freela-user-service.duckdns.org";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User register(RegisterRequest registerRequest) {
        String endpoint = "/user";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(apiUrl, registerRequest,  User.class);
        return responseEntity.getBody();
    }
}
