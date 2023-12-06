package freela.bff.domain.model.response.chat;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
public class MessageResponse {
    private Integer id;
    private Integer userIdFrom;
    private String message;
    private String time;
    private ChatResponse chat;

    public MessageResponse(Integer id, Integer userIdFrom, String message, String time, ChatResponse chat) {
        this.id = id;
        this.userIdFrom = userIdFrom;
        this.message = message;
        this.time = time;
        this.chat = chat;
    }
}
