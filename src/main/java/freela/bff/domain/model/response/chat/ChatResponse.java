package freela.bff.domain.model.response.chat;

import freela.bff.domain.model.response.user.UserNameDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ChatResponse{
    private Integer id;
    private UserNameDetails freelancerId;
    private UserNameDetails userId;
    private String lastUpdate;

    public ChatResponse(Integer id, UserNameDetails freelancerId, UserNameDetails userId, String lastUpdate) {
        this.id = id;
        this.freelancerId = freelancerId;
        this.userId = userId;
        this.lastUpdate = lastUpdate;
    }
}
