package freela.bff.domain.model.response.order;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.response.categories.SubCategory;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.domain.model.response.proposal.ProposalResponse;
import freela.bff.domain.model.response.user.User;
import freela.bff.domain.model.response.user.UserDetailsResponse;
import freela.bff.domain.model.response.user.UserNameDetails;
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
    private List<ProposalResponse> proposals;
    private List<SubCategory> subCategories;
    private List<OrderPhoto> photos;
    private UserNameDetails user;
}
