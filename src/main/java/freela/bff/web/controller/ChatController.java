package freela.bff.web.controller;

import freela.bff.domain.model.request.chat.CreateChatRequest;
import freela.bff.domain.model.response.chat.Chat;
import freela.bff.domain.model.response.chat.ChatResponse;
import freela.bff.domain.model.response.chat.Message;
import freela.bff.domain.service.ChatService;
import freela.bff.domain.service.OrdersService;
import freela.bff.infra.configuration.jwt.JwtConfiguration;
import freela.bff.infra.repository.ChatRepository;
import freela.bff.infra.repository.interfaces.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController extends BaseController {
    private final ChatRepository chatRepository;
    private final ChatService chatService;

    public ChatController(JwtConfiguration jwtConfiguration, ChatRepository chatRepository, ChatService chatService) {
        super(jwtConfiguration);
        this.chatRepository = chatRepository;
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<Chat> create(@RequestBody CreateChatRequest request){
        return ResponseEntity.ok(this.chatRepository.createChat(request));
    }

    @GetMapping
    public ResponseEntity<ArrayList<ChatResponse>> getAllByUser(@RequestParam Integer userId, @RequestParam Boolean isFreelancer){
        return ResponseEntity.ok(this.chatService.getAllChatByUser(userId, isFreelancer));
    }

    @GetMapping("/messages/{chatId}")
    public ResponseEntity<Message[]> getAllByChat(@PathVariable @NotNull Integer chatId){
        return ResponseEntity.ok(this.chatRepository.getAllMessages(chatId));
    }
}
