package freela.bff.web.controller;

import freela.bff.domain.model.request.user.LoginRequest;
import freela.bff.domain.model.request.user.RegisterRequest;
import freela.bff.domain.service.interfaces.IUserService;
import freela.bff.infra.security.jwt.JwtConfiguration;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import freela.bff.domain.model.response.user.User;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    private final JwtConfiguration jwtConfiguration;

    public UserController(IUserService userService, JwtConfiguration jwtConfiguration) {
        this.userService = userService;
        this.jwtConfiguration = jwtConfiguration;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos.",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "201", description = "Sucesso - Usuário cadastrado."),
            @ApiResponse(responseCode = "409", description = "Conflito - Já existe um usuário com as mesmas informações.")
    })
    @PostMapping
    public ResponseEntity<Void> post(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.created(URI.create("/user/" + userService.register(request))).build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Login realizado.")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.authenticate(request);

        return ResponseEntity.ok(jwtConfiguration.generateJwtToken(user));
    }
}
