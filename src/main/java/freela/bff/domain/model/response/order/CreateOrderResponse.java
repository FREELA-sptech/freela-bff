package freela.bff.domain.model.response.order;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.web.controller.UsersController;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Data
@Builder
public class CreateOrderResponse extends RepresentationModel<CreateOrderResponse> {
    public CreateOrderResponse() {
    }
}
