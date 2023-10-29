package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.domain.model.request.user.CreateUserRequest;
import freela.bff.domain.model.request.user.UpdateUserRequest;
import freela.bff.domain.model.response.categories.SubCategory;
import freela.bff.domain.model.response.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface IUsersRepository {
    User createUser(CreateUserRequest createUserRequest);
    User authenticateUser(AuthenticateUserRequest authenticateUserRequest);
    User updateProfilePhotoUser(Integer idUser, MultipartFile image);
    User getDetailsUser(Integer idUser);
    User updateUser(Integer idUser, UpdateUserRequest request);
    User[] getFreelancersUser(Integer idUser);
    SubCategory[] getSubcategoriesUser(Integer idUser);
    SubCategory[] getAllSubCategories();
}
