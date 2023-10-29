package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.request.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;

public interface IProposalsRepository {
    Proposal createProposal(Integer userId, CreateProposalRequest createProposalRequest, Integer orderId);
    Proposal findProposalsByOrder(Integer orderId);
    Proposal[] findProposalsByUserId(Integer userId);
    Proposal updateProposal(Integer proposalId, CreateProposalRequest request);
    Void deleteProposal(Integer proposalId);
    Void updateStatusProposal(Integer proposalId, EStatus status);
}
