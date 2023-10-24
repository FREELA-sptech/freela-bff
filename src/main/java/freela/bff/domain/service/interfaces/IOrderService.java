package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService {
    ResponseEntity<Order> createOrder(CreateOrderRequest request);
    ResponseEntity<Order[]> getAll(List<Integer> subCategoriesIds, String orderType);
    ResponseEntity<Order> getById(Integer orderId);
}
