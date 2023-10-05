package freela.bff.domain.service;

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
    public User register(RegisterRequest registerRequest) {
        return userRepository.register(registerRequest);
    }
}
