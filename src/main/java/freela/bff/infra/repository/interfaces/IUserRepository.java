package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.request.user.RegisterRequest;
import freela.bff.domain.model.response.user.User;

public interface IUserRepository {
    User register(RegisterRequest registerRequest);
}
