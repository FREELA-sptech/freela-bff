package freela.bff.web.controller;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.core.ErrorResponse;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.user.CreateUserResponse;
import freela.bff.domain.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Criar um novo pedido")
    @ApiResponse(responseCode = "201", description = "Sucesso - Pedido criado",
            content = @Content(schema = @Schema(implementation = CreateUserResponse.class)))
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody @Valid CreateOrderRequest request) {
        return orderService.createOrder(request);
    }
}
