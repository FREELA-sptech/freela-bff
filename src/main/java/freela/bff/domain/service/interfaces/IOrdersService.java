package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.request.order.CreateOrderBFFRequest;
import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.CreateOrderResponse;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.order.OrderResponse;
import freela.bff.infra.configuration.jwt.UserClaims;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IOrdersService {
    CreateOrderResponse createOrder(UserClaims userClaims, CreateOrderRequest request, List<MultipartFile> photos);
    OrderResponse[] getAllOrder(UserClaims userClaims);
    OrderResponse getByIdOrder(Integer orderId);
    Order updateOrder(Integer orderId, CreateOrderRequest request);
    Void deleteOrder(Integer orderId);
    OrderResponse[] getAllOrderByUser(UserClaims userClaims);
}
