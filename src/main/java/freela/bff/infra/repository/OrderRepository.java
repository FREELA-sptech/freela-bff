package freela.bff.infra.repository;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import freela.bff.infra.repository.interfaces.IOrderRepository;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Repository
public class OrderRepository extends BaseRepository implements IOrderRepository {

    private final String baseURL = "http://freela-order-service.duckdns.org/";

    public Order createOrder(CreateOrderRequest request){
        String endpoint = "/order";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpPost post = new HttpPost(apiUrl);

        return this.sendPost(this.generateBody(post,request.toString()),Order.class);
    }

    public Order[] getAll(List<Integer> subCategoriesIds, String orderType){
        String endpoint = "/order?subCategoriesIds=1";

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get,Order[].class);
    }

    public Order getById(Integer orderId){
        String endpoint = "/order/" + orderId;
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();
        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get,Order.class);
    }
}
