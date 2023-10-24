package freela.bff.domain.service.interfaces;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.order.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService {
    Order createOrder(CreateOrderRequest request);
    Order[] getAll(List<Integer> subCategoriesIds, String orderType);
    Order getById(Integer orderId);
}
