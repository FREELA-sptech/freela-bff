package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import org.springframework.http.ResponseEntity;

public interface IOrderService {
    ResponseEntity<Order> createOrder(CreateOrderRequest request);

}
