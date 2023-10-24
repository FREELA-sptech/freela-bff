package freela.bff.infra.repository;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.user.User;
import freela.bff.infra.repository.interfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Repository
public class OrderRepository implements IOrderRepository {

    private final String baseURL = "http://freela-order-service.duckdns.org";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Order> createOrder(CreateOrderRequest request){
        String endpoint = "/order";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        ResponseEntity<Order> responseEntity = restTemplate.postForEntity(apiUrl, request,  Order.class);
        return responseEntity;
    }

    public ResponseEntity<Order[]> getAll(List<Integer> subCategoriesIds, String orderType){
        String endpoint = "/order";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();
        ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity(apiUrl, Order[].class,subCategoriesIds,orderType);
        return  responseEntity;
    }

    public ResponseEntity<Order> getById(Integer orderId){
        String endpoint = "/order/" + orderId;
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        return restTemplate.getForEntity(apiUrl, Order.class);
    }
}
