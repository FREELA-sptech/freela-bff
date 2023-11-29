package freela.bff.domain.service;

import freela.bff.domain.model.response.chat.Chat;
import freela.bff.domain.model.response.chat.ChatResponse;
import freela.bff.domain.model.response.user.User;
import freela.bff.domain.model.response.user.UserNameDetails;
import freela.bff.domain.service.interfaces.IChatService;
import freela.bff.infra.repository.interfaces.IChatRepository;
import freela.bff.infra.repository.interfaces.IUsersRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class ChatService implements IChatService {
    private final IChatRepository chatRepository;
    private final IUsersRepository usersRepository;

    public ChatService(IChatRepository chatRepository, IUsersRepository usersRepository) {
        this.chatRepository = chatRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public ArrayList<ChatResponse> getAllChatByUser(Integer userId, Boolean isFreelancer) {
        Chat[] chats = this.chatRepository.getAllChatByUser(userId, isFreelancer);

        ArrayList<ChatResponse> response = new ArrayList<>();

        for (Chat chat : chats) {
            UserNameDetails user = new UserNameDetails(this.usersRepository.getDetailsUser(chat.getUserId()));
            UserNameDetails freelancer = new UserNameDetails(this.usersRepository.getDetailsUser(chat.getFreelancerId()));

            response.add(new ChatResponse(chat.getId(), freelancer, user, chat.getLastUpdate()));
        }

        return response;
    }
}
