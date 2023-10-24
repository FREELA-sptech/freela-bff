package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import org.springframework.http.ResponseEntity;

public interface IOrderRepository {
    ResponseEntity<Order> createOrder(CreateOrderRequest request);
}
