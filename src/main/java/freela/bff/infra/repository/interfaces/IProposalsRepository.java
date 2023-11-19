package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.request.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.domain.model.response.proposal.ProposalResponse;

public interface IProposalsRepository {
    ProposalResponse createProposal(Integer userId, CreateProposalRequest createProposalRequest, Integer orderId);
    Proposal findProposalsByOrder(Integer orderId);
    ProposalResponse[] findProposalsByUserId(Integer userId);
    ProposalResponse updateProposal(Integer proposalId, CreateProposalRequest request);
    Void deleteProposal(Integer proposalId);
    Void updateStatusProposal(Integer proposalId, EStatus status);
}
