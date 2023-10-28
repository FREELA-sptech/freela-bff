package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.CreateOrderResponse;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.user.CreateUserResponse;
import freela.bff.infra.configuration.jwt.UserClaims;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService {
    CreateOrderResponse createOrder(UserClaims userClaims, CreateOrderRequest request);
    Order[] getAllOrder(UserClaims userClaims);
    Order getByIdOrder(Integer orderId);
    Order updateOrder(Integer orderId, CreateOrderRequest request);
    Void deleteOrder(Integer orderId);
}
