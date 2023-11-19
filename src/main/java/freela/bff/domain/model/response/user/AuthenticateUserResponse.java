package freela.bff.domain.model.response.user;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.web.controller.UsersController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.core.Authentication;

import java.io.IOException;
@Data
@NoArgsConstructor
@Builder
public class AuthenticateUserResponse extends RepresentationModel<AuthenticateUserResponse> {
    private String token;
    private boolean isFreelancer;
    private Integer userId;

    public AuthenticateUserResponse(String token, boolean isFreelancer, Integer userId) {
        this.token = token;
        this.isFreelancer = isFreelancer;
        this.userId = userId;

        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsersController.class).updateProfilePhotoUser( null))
                .withRel("updatedProfilePhoto"));
    }
}
