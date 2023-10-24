package freela.bff.domain.model.response.order;

import freela.bff.domain.model.response.proposal.Proposal;

import javax.persistence.OneToOne;

public class Order {
    private Integer id;
    private String description;
    private String title;
    private Double value;
    private String deadline;
    private Integer userId;
    private Proposal proposalAccepted;
    private boolean isAccepted;
}
