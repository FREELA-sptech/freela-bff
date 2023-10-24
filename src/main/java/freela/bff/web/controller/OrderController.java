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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @Operation(summary = "Listar pedidos")
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping
    public ResponseEntity<Order[]> getAll(
            @RequestParam(required = true, name = "subCategoriesIds") List<Integer> subCategoriesIds, @RequestParam(required = false, name = "orderType") String orderType) {
        return orderService.getAll(subCategoriesIds,orderType);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "404", description =
                    "Ordem não encontrada.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Ordem Encontrada.")
    })
    @GetMapping("{orderId}")
    public ResponseEntity<Order> getById(@PathVariable Integer orderId) {
        return orderService.getById(orderId);
    }
}
