package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.domain.model.request.user.CreateUserRequest;
import freela.bff.domain.model.request.user.UpdateUserRequest;
import freela.bff.domain.model.response.user.AuthenticateUserResponse;
import freela.bff.domain.model.response.user.User;
import freela.bff.domain.model.response.user.CreateUserResponse;
import freela.bff.infra.configuration.jwt.UserClaims;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface IUsersService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);
    AuthenticateUserResponse authenticateUser(AuthenticateUserRequest authenticateUserRequest);
    User updateProfilePhotoUser(Authentication authentication, MultipartFile image);
    User updateUser(UserClaims userClaims, UpdateUserRequest request);
    User[] getFreelancers(UserClaims userClaims);
    User getDetailsUser(UserClaims userClaims);
}
