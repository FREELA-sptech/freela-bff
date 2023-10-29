package freela.bff.domain.model.response.proposal;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.response.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Proposal {
    private Integer id;
    private Double value;
    private Integer userId;
    private String description;
    private String deadline;
    private EStatus status;
    private Order order;
}
