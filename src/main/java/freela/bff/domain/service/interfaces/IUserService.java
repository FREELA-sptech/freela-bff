package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.request.user.RegisterRequest;
import freela.bff.domain.model.response.user.User;

public interface IUserService {
    User register(RegisterRequest registerRequest);
}
