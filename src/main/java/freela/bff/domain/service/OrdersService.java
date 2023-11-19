package freela.bff.domain.service;

import freela.bff.domain.model.mapper.user.UserDetailsMapper;
import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.categories.SubCategory;
import freela.bff.domain.model.response.order.CreateOrderResponse;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.order.OrderResponse;
import freela.bff.domain.model.response.user.User;
import freela.bff.domain.service.interfaces.IOrdersService;
import freela.bff.infra.configuration.jwt.UserClaims;
import freela.bff.infra.repository.OrdersRepository;
import freela.bff.infra.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService implements IOrdersService {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private UsersRepository usersRepository;

    public CreateOrderResponse createOrder(UserClaims userClaims, CreateOrderRequest request){
        request.setUserId(userClaims.getUserId());
        Order order = orderRepository.createOrder(request);
        return new CreateOrderResponse();
    }

    public OrderResponse[] getAllOrder(UserClaims userClaims) {
        SubCategory[] subCategories = usersRepository.getSubcategoriesUser(userClaims.getUserId());

        List<Integer> subcategoryIds = Arrays.stream(subCategories)
                .map(SubCategory::getId)
                .collect(Collectors.toList());

        return orderRepository.getAllOrder(subcategoryIds);
    }


    public OrderResponse getByIdOrder(Integer orderId){
        OrderResponse order = orderRepository.getByIdOrder(orderId);

        return order;
    }

    @Override
    public Order updateOrder(Integer orderId, CreateOrderRequest request) {
        return orderRepository.updateOrder(orderId, request);
    }

    @Override
    public Void deleteOrder(Integer orderId) {
        return orderRepository.deleteOrder(orderId);
    }

    @Override
    public OrderResponse[] getAllOrderByUser(UserClaims userClaims) {
        return orderRepository.getAllOrderByUser(userClaims.getUserId());
    }
}
