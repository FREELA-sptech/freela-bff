package freela.bff.infra.repository.interfaces;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderRepository {
    Order createOrder(CreateOrderRequest request);
    Order[] getAll(List<Integer> subCategoriesIds, String orderType);
    Order getById(Integer orderId);
}
