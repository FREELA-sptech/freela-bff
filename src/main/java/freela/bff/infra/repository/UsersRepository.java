package freela.bff.infra.repository;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.domain.model.request.user.CreateUserRequest;
import freela.bff.infra.repository.interfaces.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import freela.bff.domain.model.response.user.User;

import java.io.IOException;

@Repository
public class UsersRepository implements IUsersRepository {
    private final String baseURL = "http://freela-user-service.duckdns.org";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        String endpoint = "/user";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(apiUrl, createUserRequest,  User.class);
        return responseEntity.getBody();
    }

    @Override
    public User authenticateUser(AuthenticateUserRequest authenticateUserRequest) {
        String endpoint = "/user/login";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(apiUrl, authenticateUserRequest,  User.class);
        return responseEntity.getBody();
    }

    @Override
    public User updateProfilePhotoUser(Integer idUser, MultipartFile image) {
        String endpoint = String.format("/user/login/%s", idUser);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(apiUrl, image,  User.class);
        return responseEntity.getBody();
    }
}
