package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.response.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import org.springframework.http.ResponseEntity;

public interface IProposalRepository {
    ResponseEntity<Proposal> create(Integer userId, CreateProposalRequest createProposalRequest, Integer orderId);
    ResponseEntity<Proposal> findProposalsByOrder(Integer orderId);
    ResponseEntity<Proposal[]> findProposalsByUserId(Integer userId);
}
