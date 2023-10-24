package freela.bff.domain.service;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.service.interfaces.IOrderService;
import freela.bff.infra.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<Order> createOrder(CreateOrderRequest request){
        return orderRepository.createOrder(request);
    }
}
