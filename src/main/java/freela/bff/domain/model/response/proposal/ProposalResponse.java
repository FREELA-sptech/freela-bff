package freela.bff.domain.model.response.proposal;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.user.UserNameDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProposalResponse {
    private Integer id;
    private Double value;
    private Integer userId;
    private String description;
    private String deadline;
    private EStatus status;
    private Order order;
    private UserNameDetails user;
}
