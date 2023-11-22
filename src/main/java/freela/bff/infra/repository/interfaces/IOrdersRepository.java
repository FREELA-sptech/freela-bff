package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.request.order.CreateOrderBFFRequest;
import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.order.OrderResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IOrdersRepository {
    Order createOrder(CreateOrderRequest request, List<MultipartFile> photos);
    OrderResponse[] getAllOrder(List<Integer> subCategoriesIds);
    OrderResponse getByIdOrder(Integer orderId);
    Order updateOrder(Integer orderId, CreateOrderRequest request);
    Void deleteOrder(Integer orderId);
    OrderResponse[] getAllOrderByUser(Integer userId);
}
