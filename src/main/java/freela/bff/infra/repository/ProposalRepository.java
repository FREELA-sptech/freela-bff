package freela.bff.infra.repository;

import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.infra.repository.interfaces.IProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class ProposalRepository implements IProposalRepository {

    private final String baseURL = "http://freela-order-service.duckdns.org/proposal/";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Proposal> create(Integer userId, CreateProposalRequest createProposalRequest, Integer orderId){
        String endpoint = orderId + "/" + userId;

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        return restTemplate.postForEntity(apiUrl,createProposalRequest, Proposal.class);
    }

    public ResponseEntity<Proposal> findProposalsByOrder(Integer orderId){
        String endpoint = "/order/" + orderId;

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();
        return restTemplate.getForEntity(apiUrl, Proposal.class);
    }

    public ResponseEntity<Proposal[]> findProposalsByUserId(Integer userId){
        String endpoint = "/user/" + userId;

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();
        return restTemplate.getForEntity(apiUrl, Proposal[].class);
    }
}
