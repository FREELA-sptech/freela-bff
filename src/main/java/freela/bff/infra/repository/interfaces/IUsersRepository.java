package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.domain.model.request.user.CreateUserRequest;
import freela.bff.domain.model.response.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUsersRepository {
    User createUser(CreateUserRequest createUserRequest);
    User authenticateUser(AuthenticateUserRequest authenticateUserRequest);
    User updateProfilePhotoUser(Integer idUser, MultipartFile image);
}
