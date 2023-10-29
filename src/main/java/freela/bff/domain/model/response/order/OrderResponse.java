package freela.bff.domain.model.response.order;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.response.categories.SubCategory;
import freela.bff.domain.model.response.proposal.Proposal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private String description;
    private String title;
    private Double value;
    private String deadline;
    private Integer userId;
    private Proposal proposalAccepted;
    private EStatus status;
    private List<Proposal> proposals;
    private List<SubCategory> subCategories;
}
