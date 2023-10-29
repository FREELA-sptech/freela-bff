package freela.bff.infra.repository;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.request.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.infra.repository.interfaces.IProposalsRepository;
import org.apache.http.client.methods.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class ProposalsRepository extends BaseRepository  implements IProposalsRepository {

    private final String baseURL = "http://freela-order-service.duckdns.org/";

    public Proposal createProposal(Integer userId, CreateProposalRequest createProposalRequest, Integer orderId){
        String endpoint = "/proposal/" + orderId + "/" + userId;

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpPost post = new HttpPost(apiUrl);

        return this.sendPost(this.generateBody(post,createProposalRequest),Proposal.class);
    }

    public Proposal findProposalsByOrder(Integer orderId){
        String endpoint = "/order/" + orderId;

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();
        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get,Proposal.class);
    }

    public Proposal[] findProposalsByUserId(Integer userId){
        String endpoint = "/proposal/user/" + userId;

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get,Proposal[].class);
    }

    @Override
    public Proposal updateProposal(Integer proposalId, CreateProposalRequest request) {
        String endpoint = String.format("/proposal/%s", proposalId);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();


        HttpPut put = new HttpPut(apiUrl);

        return this.sendPut(this.generateBody(put, request), Proposal.class);
    }

    @Override
    public Void deleteProposal(Integer proposalId) {
        String endpoint = String.format("/proposal/%s", proposalId);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();


        HttpDelete delete = new HttpDelete(apiUrl);

        this.sendDelete(delete, Boolean.class);

        return null;
    }

    @Override
    public Void updateStatusProposal(Integer proposalId, EStatus status) {
        String endpoint = String.format("/proposal/status/%s", proposalId);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .queryParam("status", status)
                .toUriString();


        HttpPut put = new HttpPut(apiUrl);

        this.sendPut(put, Boolean.class);

        return null;
    }
}
