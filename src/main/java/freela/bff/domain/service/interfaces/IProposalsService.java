package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.request.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.domain.model.response.proposal.ProposalResponse;
import freela.bff.infra.configuration.jwt.UserClaims;

public interface IProposalsService {
    ProposalResponse createProposal(UserClaims userClaims, CreateProposalRequest createProposalRequest, Integer orderId);
    Proposal findProposalsByOrder(Integer orderId);
    ProposalResponse[] findProposalsByUserId(UserClaims userClaims);
    ProposalResponse updateProposal(Integer proposalId, CreateProposalRequest request);
    Void deleteProposal(Integer proposalId);
    Void updateStatusProposal(Integer proposalId, EStatus status);
}
