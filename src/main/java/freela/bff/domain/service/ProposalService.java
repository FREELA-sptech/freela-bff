package freela.bff.domain.service;

import freela.bff.domain.model.response.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.domain.service.interfaces.IProposalService;
import freela.bff.infra.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProposalService implements IProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    public ResponseEntity<Proposal> create(Integer userId, CreateProposalRequest createProposalRequest, Integer orderId){
            return proposalRepository.create(userId,createProposalRequest,orderId);
    }

    public ResponseEntity<Proposal> findProposalsByOrder(Integer orderId){
        return proposalRepository.findProposalsByOrder(orderId);
    }

    public ResponseEntity<Proposal[]> findProposalsByUserId(Integer userId){
        return proposalRepository.findProposalsByUserId(userId);
    }
}
