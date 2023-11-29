package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.request.chat.CreateChatRequest;
import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.chat.Chat;
import freela.bff.domain.model.response.order.Order;
import org.springframework.web.multipart.MultipartFile;
import freela.bff.domain.model.response.chat.Message;
import java.util.List;

public interface IChatRepository {
    Chat createChat(CreateChatRequest chatRequest);
    Chat[] getAllChatByUser(Integer userId, Boolean isFreelancer);
    Message[] getAllMessages(Integer chatId);
}
