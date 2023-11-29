package freela.bff.domain.model.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequest {
    private String message;
    private String time;
    private Integer chatId;
    private Integer to;
}
