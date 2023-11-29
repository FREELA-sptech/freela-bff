package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.response.chat.Chat;
import freela.bff.domain.model.response.chat.ChatResponse;

import java.util.ArrayList;

public interface IChatService {
    ArrayList<ChatResponse> getAllChatByUser(Integer userId, Boolean isFreelancer);
}
