package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.response.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import org.springframework.http.ResponseEntity;

public interface IProposalRepository {
    Proposal create(Integer userId, CreateProposalRequest createProposalRequest, Integer orderId);
    Proposal findProposalsByOrder(Integer orderId);
    Proposal[] findProposalsByUserId(Integer userId);
}
