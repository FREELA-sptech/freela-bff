package freela.bff.domain.model.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {
    private Integer freelancerId;
    private Integer userId;
    private Integer orderId;
    private String lastUpdate;
}
