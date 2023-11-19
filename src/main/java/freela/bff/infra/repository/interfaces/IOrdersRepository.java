package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.order.OrderResponse;

import java.util.List;

public interface IOrdersRepository {
    Order createOrder(CreateOrderRequest request);
    OrderResponse[] getAllOrder(List<Integer> subCategoriesIds);
    OrderResponse getByIdOrder(Integer orderId);
    Order updateOrder(Integer orderId, CreateOrderRequest request);
    Void deleteOrder(Integer orderId);
    OrderResponse[] getAllOrderByUser(Integer userId);
}
