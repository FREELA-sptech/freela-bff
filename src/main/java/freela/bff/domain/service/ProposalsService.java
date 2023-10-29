package freela.bff.domain.service;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.request.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.domain.service.interfaces.IProposalsService;
import freela.bff.infra.configuration.jwt.UserClaims;
import freela.bff.infra.repository.ProposalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalsService implements IProposalsService {

    @Autowired
    private ProposalsRepository proposalRepository;

    public Proposal createProposal(UserClaims userClaims, CreateProposalRequest createProposalRequest, Integer orderId){
            return proposalRepository.createProposal(userClaims.getUserId(), createProposalRequest, orderId);
    }

    public Proposal findProposalsByOrder(Integer orderId){
        return proposalRepository.findProposalsByOrder(orderId);
    }

    public Proposal[] findProposalsByUserId(UserClaims userClaims){
        return proposalRepository.findProposalsByUserId(userClaims.getUserId());
    }

    @Override
    public Proposal updateProposal(Integer proposalId, CreateProposalRequest request) {
        return proposalRepository.updateProposal(proposalId, request);
    }

    @Override
    public Void deleteProposal(Integer proposalId) {
        return proposalRepository.deleteProposal(proposalId);
    }

    @Override
    public Void updateStatusProposal(Integer proposalId, EStatus status) {
        return proposalRepository.updateStatusProposal(proposalId, status);
    }
}
