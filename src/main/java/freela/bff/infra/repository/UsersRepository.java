package freela.bff.infra.repository;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.domain.model.request.user.CreateUserRequest;
import freela.bff.domain.model.request.user.UpdateUserRequest;
import freela.bff.domain.model.response.categories.SubCategory;
import freela.bff.domain.model.response.order.Order;
import freela.bff.infra.repository.interfaces.IUsersRepository;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import freela.bff.domain.model.response.user.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersRepository extends BaseRepository implements IUsersRepository {
    private final String baseURL = "http://freela-user-service.duckdns.org";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        String endpoint = "/user";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpPost post = new HttpPost(apiUrl);

        return this.sendPost(this.generateBody(post, createUserRequest), User.class);
    }

    @Override
    public User authenticateUser(AuthenticateUserRequest authenticateUserRequest) {
        String endpoint = "/user/login";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpPost post = new HttpPost(apiUrl);

        User response = this.sendPost(this.generateBody(post, authenticateUserRequest), User.class);

        return response;
    }

    @Override
    public User updateProfilePhotoUser(Integer idUser, MultipartFile image) {
        String endpoint = String.format("/user/photo/%s", idUser);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpPost post = new HttpPost(apiUrl);

        return this.sendPost(this.generateBody(post, image.toString()), User.class);
    }

    @Override
    public User updateUser(Integer idUser, UpdateUserRequest request) {
        String endpoint = String.format("/user/%s", idUser);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();


        HttpPatch patch = new HttpPatch(apiUrl);

        return this.sendPatch(this.generateBody(patch, request), User.class);
    }

    @Override
    public User getDetailsUser(Integer idUser) {
        String endpoint = String.format("/user/%s", idUser);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get, User.class);
    }

    @Override
    public User[] getFreelancersUser(Integer idUser) {
        String endpoint = String.format("/user/by-subcategories/%s", idUser);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get, User[].class);
    }

    @Override
    public SubCategory[] getSubcategoriesUser(Integer idUser) {
        String endpoint = String.format("/user/subcategories/%s", idUser);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get, SubCategory[].class);
    }

    @Override
    public SubCategory[] getAllSubCategories() {
        String endpoint = "/sub-categories";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get, SubCategory[].class);
    }
}
