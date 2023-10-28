package freela.bff.web.controller;

import freela.bff.domain.model.request.order.CreateOrderRequest;
import freela.bff.domain.model.response.core.ErrorResponse;
import freela.bff.domain.model.response.order.CreateOrderResponse;
import freela.bff.domain.model.response.order.Order;
import freela.bff.domain.model.response.user.CreateUserResponse;
import freela.bff.domain.service.OrderService;
import freela.bff.infra.configuration.jwt.JwtConfiguration;
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
public class OrderController extends BaseController {

    private final OrderService orderService;

    public OrderController(JwtConfiguration jwtConfiguration, OrderService orderService) {
        super(jwtConfiguration);
        this.orderService = orderService;
    }

    @Operation(summary = "Criar um novo pedido")
    @ApiResponse(responseCode = "201", description = "Sucesso - Pedido criado",
            content = @Content(schema = @Schema(implementation = CreateUserResponse.class)))
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(
            @RequestBody @Valid CreateOrderRequest request) {
        var response = orderService.createOrder(this.getUserClaims(), request);
        return ResponseEntity.created(URI.create("/order/" + response)).body(response);
    }

    @Operation(summary = "Listar pedidos por subcategorias do usuario")
    @ApiResponse(responseCode = "200", description = "Sucesso - Lista de pedidos",
            content = @Content(schema = @Schema(implementation = CreateUserResponse[].class)))
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping
    public ResponseEntity<Order[]> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder(this.getUserClaims()));

    }

    @Operation(summary = "Buscar detalhes de um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description =
                    "Ordem não encontrada.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Detalhes da ordem.")
    })
    @GetMapping("{orderId}")
    public ResponseEntity<Order> getByIdOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.getByIdOrder(orderId));
    }

    @Operation(summary = "Editar um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description =
                    "Ordem não encontrada.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Detalhes da ordem.")
    })
    @PatchMapping("{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer orderId, @RequestBody @Valid CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, request));
    }

    @Operation(summary = "Deletar um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description =
                    "Ordem não encontrada.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Detalhes da ordem.")
    })
    @DeleteMapping("{orderId}")
    public ResponseEntity<Void> updateOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
