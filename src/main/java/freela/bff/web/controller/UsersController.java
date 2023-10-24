package freela.bff.web.controller;

import freela.bff.domain.model.request.user.AuthenticateUserRequest;
import freela.bff.domain.model.request.user.CreateUserRequest;
import freela.bff.domain.model.request.user.UpdateUserRequest;
import freela.bff.domain.model.response.core.ErrorResponse;
import freela.bff.domain.model.response.user.AuthenticateUserResponse;
import freela.bff.domain.model.response.user.CreateUserResponse;
import freela.bff.domain.service.interfaces.IUsersService;
import freela.bff.infra.configuration.jwt.JwtConfiguration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import freela.bff.domain.model.response.user.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {
    private final IUsersService usersService;

    public UsersController(JwtConfiguration jwtConfiguration, IUsersService usersService) {
        super(jwtConfiguration);
            this.usersService = usersService;
    }

    @Operation(summary = "Cadastrar um novo usuário no sistema")
    @ApiResponse(responseCode = "201", description = "Sucesso - Usuário cadastrado",
            content = @Content(schema = @Schema(implementation = CreateUserResponse.class)))
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Conflito - Já existe um usuário com as mesmas informações",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody @Valid CreateUserRequest request) {
        var response = usersService.createUser(request);
        return ResponseEntity.created(URI.create("/user/" + response)).body(response);
    }

    @Operation(summary = "Buscar detalhes do usuário")
    @ApiResponse(responseCode = "200", description = "Sucesso - Dados do usuário",
            content = @Content(schema = @Schema(implementation = CreateUserResponse.class)))
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized - Token invalido ou expirado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping
    public ResponseEntity<User> getDetailsUser() {
        var response = usersService.getDetailsUser(this.getUserClaims());
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Buscar freelancers")
    @ApiResponse(responseCode = "200", description = "",
            content = @Content(schema = @Schema(implementation = CreateUserResponse.class)))
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized - Token invalido ou expirado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/freelancers")
    public ResponseEntity<User[]> getFreelancers() {
        var response = usersService.getFreelancers(this.getUserClaims());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Realizar a autenticação de um usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Sucesso - Usuário autenticado",
            content = @Content(schema = @Schema(implementation = AuthenticateUserResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not Found - Email do usuário não encontrado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized - Senha incorreta",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateUserResponse> authenticateUser(
            @Valid @RequestBody AuthenticateUserRequest request) {
        return ResponseEntity.ok(usersService.authenticateUser(request));
    }

    @Operation(summary = "Atualizar foto do perfil do usuario")
    @ApiResponse(responseCode = "200", description = "Sucesso - Foto de perfil atualizada",
            content = @Content(schema = @Schema(implementation = AuthenticateUserResponse.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized - Token invalido ou expirado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping("/profile-photo")
    public ResponseEntity<User> updateProfilePhotoUser(
            Authentication authentication, @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(usersService.updateProfilePhotoUser(authentication, image));
    }

    @Operation(summary = "Atualizar perfil do usuario")
    @ApiResponse(responseCode = "200", description = "Perfil atualizado",
            content = @Content(schema = @Schema(implementation = AuthenticateUserResponse.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized - Token invalido ou expirado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(usersService.updateUser(this.getUserClaims(), request));
    }

}
