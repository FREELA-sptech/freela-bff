package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.CreateOrderResponse;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.order.OrderResponse;
import freela.bff.infra.configuration.jwt.UserClaims;

public interface IOrdersService {
    CreateOrderResponse createOrder(UserClaims userClaims, CreateOrderRequest request);
    OrderResponse[] getAllOrder(UserClaims userClaims);
    OrderResponse getByIdOrder(Integer orderId);
    Order updateOrder(Integer orderId, CreateOrderRequest request);
    Void deleteOrder(Integer orderId);
}
