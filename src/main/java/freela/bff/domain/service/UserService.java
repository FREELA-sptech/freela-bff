package freela.bff.domain.service;

import freela.bff.domain.model.request.user.LoginRequest;
import freela.bff.domain.model.request.user.RegisterRequest;
import freela.bff.domain.model.response.user.User;
import freela.bff.domain.service.interfaces.IUserService;
import freela.bff.infra.repository.interfaces.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Integer register(RegisterRequest registerRequest) {
        User user = userRepository.register(registerRequest);

        return user.getId();
    }

    @Override
    public User authenticate(LoginRequest loginRequest) {
        User user = userRepository.authenticate(loginRequest);
        return user;
    }
}
