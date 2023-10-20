package freela.bff.domain.model.response.user;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.web.controller.UsersController;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.io.IOException;

@Data
@Builder
public class CreateUserResponse extends RepresentationModel<CreateUserResponse> {
    public CreateUserResponse() {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsersController.class).authenticateUser(new AuthenticateUserRequest())).withRel("authenticateUser"));
    }
}
