package freela.bff.infra.repository;

import freela.bff.domain.model.request.chat.CreateChatRequest;
import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.chat.Chat;
import freela.bff.domain.model.response.chat.Message;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.user.User;
import freela.bff.infra.repository.interfaces.IChatRepository;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Repository
public class ChatRepository extends BaseRepository  implements IChatRepository {
    private final String baseURL = "http://freela-chat-service.duckdns.org";

    @Override
    public Chat createChat(CreateChatRequest chatRequest) {
        String endpoint = "/chats";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpPost post = new HttpPost(apiUrl);

        return this.sendPost(this.generateBody(post, chatRequest), Chat.class);
    }

    @Override
    public Chat[] getAllChatByUser(Integer userId, Boolean isFreelancer) {
        String endpoint = "chats";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .queryParam("userId", userId)
                .queryParam("isFreelancer", isFreelancer)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get, Chat[].class);
    }

    @Override
    public Message[] getAllMessages(Integer chatId) {
        String endpoint = String.format("chats/messages/%s", chatId);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get, Message[].class);
    }
}
