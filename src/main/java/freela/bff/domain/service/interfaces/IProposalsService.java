package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.request.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.infra.configuration.jwt.UserClaims;

public interface IProposalsService {
    Proposal createProposal(UserClaims userClaims, CreateProposalRequest createProposalRequest, Integer orderId);
    Proposal findProposalsByOrder(Integer orderId);
    Proposal[] findProposalsByUserId(UserClaims userClaims);
    Proposal updateProposal(Integer proposalId, CreateProposalRequest request);
    Void deleteProposal(Integer proposalId);
    Void updateStatusProposal(Integer proposalId, EStatus status);
}
