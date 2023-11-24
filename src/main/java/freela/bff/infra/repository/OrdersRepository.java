package freela.bff.infra.repository;

import freela.bff.domain.model.request.order.CreateOrderBFFRequest;
import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.request.order.UpdateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.order.OrderResponse;
import freela.bff.infra.repository.interfaces.IOrdersRepository;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Repository;
import org.apache.http.client.methods.HttpGet;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrdersRepository extends BaseRepository implements IOrdersRepository {

    private final String baseURL = "http://freela-order-service.duckdns.org";

    public Order createOrder(CreateOrderRequest request, List<MultipartFile> photos){
        String endpoint = "/order";
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpPost post = new HttpPost(apiUrl);

        try {
            return this.sendPost(this.generateMultipartBody(post, request, photos),Order.class);
        } catch (IOException ex) {

        }
        return null;
    }

    public OrderResponse[] getAllOrder(List<Integer> subCategoriesIds){
        String subCategories = String.join(",", subCategoriesIds.stream().map(Object::toString).collect(Collectors.toList()));

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path("/order")
                .queryParam("subCategoriesIds", subCategories)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get, OrderResponse[].class);
    }

    public OrderResponse getByIdOrder(Integer orderId){
        String endpoint = "/order/" + orderId;
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get, OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrder(Integer orderId, UpdateOrderRequest updateOrderRequest, ArrayList<MultipartFile> newPhotos) {
        String endpoint = String.format("/order/%s", orderId);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();


        HttpPatch patch = new HttpPatch(apiUrl);

        try {
            return this.sendPatch(this.generateMultipartBodyUpdateOrder(patch, updateOrderRequest, newPhotos), OrderResponse.class);
        } catch (IOException ex) {

        }
        return null;
    }

    @Override
    public Void deleteOrder(Integer orderId) {
        String endpoint = String.format("/order/%s", orderId);
        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();


        HttpDelete delete = new HttpDelete(apiUrl);

        this.sendDelete(delete, Boolean.class);

        return null;
    }

    @Override
    public OrderResponse[] getAllOrderByUser(Integer userId) {
        String endpoint = String.format("/order/user/%s", userId);

        String apiUrl = UriComponentsBuilder.fromUriString(baseURL)
                .path(endpoint)
                .toUriString();

        HttpGet get = new HttpGet(apiUrl);

        return this.sendGet(get, OrderResponse[].class);
    }
}
