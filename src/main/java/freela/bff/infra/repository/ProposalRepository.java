package freela.bff.infra.repository;

import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.infra.repository.interfaces.IProposalRepository;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class ProposalRepository extends BaseRepository  implements IProposalRepository {

    private final String baseURL = "http://freela-order-service.duckdns.org/proposal/";

    public Proposal create(Integer userId, CreateProposalRequest createProposalRequest, Integer orderId){
        String endpoint = orderId + "/" + userId;

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpPost post = new HttpPost(apiUrl);

        return this.sendPost(this.generateBody(post,createProposalRequest.toString()),Proposal.class);
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
        String endpoint = "/user/" + userId;

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get,Proposal[].class);
    }
}
