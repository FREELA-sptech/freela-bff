package freela.bff.web.controller;

import freela.bff.domain.model.response.categories.SubCategory;
import freela.bff.domain.model.response.core.ErrorResponse;
import freela.bff.domain.model.response.user.CreateUserResponse;
import freela.bff.domain.model.response.user.UserDetailsResponse;
import freela.bff.domain.service.ProposalsService;
import freela.bff.domain.service.interfaces.IUsersService;
import freela.bff.infra.configuration.jwt.JwtConfiguration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sub-categories")
public class SubCategoriesController {
    @Autowired
    private IUsersService usersService;

    @Operation(summary = "Buscar todas as sub categorias")
    @ApiResponse(responseCode = "200", description = "Sucesso - Dados das sub categorias",
            content = @Content(schema = @Schema(implementation = CreateUserResponse.class)))
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping
    public ResponseEntity<SubCategory[]> getDetailsUser() {
        return ResponseEntity.ok(usersService.getAllSubCategories());
    }
}
