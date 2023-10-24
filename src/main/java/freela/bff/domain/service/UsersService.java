package freela.bff.domain.service;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.domain.model.request.user.CreateUserRequest;
import freela.bff.domain.model.request.user.UpdateUserRequest;
import freela.bff.domain.model.response.user.AuthenticateUserResponse;
import freela.bff.domain.model.response.user.User;
import freela.bff.domain.model.response.user.CreateUserResponse;
import freela.bff.domain.service.interfaces.IUsersService;
import freela.bff.infra.configuration.jwt.JwtConfiguration;
import freela.bff.infra.configuration.jwt.UserClaims;
import freela.bff.infra.repository.interfaces.IUsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class UsersService implements IUsersService {
    private final IUsersRepository usersRepository;
    private final JwtConfiguration jwtConfiguration;

    public UsersService(IUsersRepository usersRepository, JwtConfiguration jwtConfiguration) {
        this.usersRepository = usersRepository;
        this.jwtConfiguration = jwtConfiguration;
    }
    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        User user = usersRepository.createUser(createUserRequest);

        return new CreateUserResponse();
    }

    @Override
    public AuthenticateUserResponse authenticateUser(AuthenticateUserRequest authenticateUserRequest) {
        User user = usersRepository.authenticateUser(authenticateUserRequest);

        return new AuthenticateUserResponse(jwtConfiguration.generateJwtToken(user), user.getIsFreelancer());
    }

    @Override
    public User updateProfilePhotoUser(Authentication authentication, MultipartFile image) {

        return usersRepository.updateProfilePhotoUser(1, image);
    }

    @Override
    public User updateUser(UserClaims userClaims, UpdateUserRequest request) {
        return usersRepository.updateUser(userClaims.getUserId(), request);
    }

    @Override
    public User getDetailsUser(UserClaims userClaims) {
        return usersRepository.getDetailsUser(userClaims.getUserId());
    }

    @Override
    public User[] getFreelancers(UserClaims userClaims) {
        return usersRepository.getFreelancers(userClaims.getUserId());
    }
}
