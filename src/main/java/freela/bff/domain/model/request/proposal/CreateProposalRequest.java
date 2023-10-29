package freela.bff.domain.model.request.proposal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProposalRequest {
    private Double value;
    private String description;
    private String deadline;
}
