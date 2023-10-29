package freela.bff.web.controller;

import freela.bff.domain.model.enums.EStatus;
import freela.bff.domain.model.response.core.ErrorResponse;
import freela.bff.domain.model.request.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.domain.model.response.user.CreateUserResponse;
import freela.bff.domain.service.ProposalsService;
import freela.bff.infra.configuration.jwt.JwtConfiguration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/proposals")
public class ProposalsController extends BaseController {
    @Autowired
    private ProposalsService proposalsService;

    public ProposalsController(JwtConfiguration jwtConfiguration, ProposalsService proposalsService) {
        super(jwtConfiguration);
        this.proposalsService = proposalsService;
    }

    @Operation(summary = "Criar uma nova proposta para uma ordem")
    @ApiResponse(responseCode = "201", description = "Sucesso - Proposta criada",
            content = @Content(schema = @Schema(implementation = CreateUserResponse.class)))
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping("/{orderId}")
    public ResponseEntity<Proposal> createProposal(
            @PathVariable @NotNull int orderId,
            @RequestBody CreateProposalRequest request
    ){
        return ResponseEntity.ok(proposalsService.createProposal(this.getUserClaims(),request,orderId));
    }

    @Operation(summary = "Buscar propostas de um usuario")
    @ApiResponse(responseCode = "201", description = "Sucesso - Lista de propostas",
            content = @Content(schema = @Schema(implementation = CreateUserResponse.class)))
    @ApiResponse(responseCode = "400", description = "BadRequest - Parâmetros incorretos ou insuficientes",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/user")
    public ResponseEntity<Proposal[]> findProposalsByUserid() {
        return ResponseEntity.ok(proposalsService.findProposalsByUserId(this.getUserClaims()));
    }

    @Operation(summary = "Editar uma proposta")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description =
                    "Ordem não encontrada.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Proposta ediada.")
    })
    @PatchMapping("/{proposalId}")
    public ResponseEntity<Proposal> updateProposal(@PathVariable Integer proposalId, @RequestBody @Valid CreateProposalRequest request) {
        return ResponseEntity.ok(proposalsService.updateProposal(proposalId, request));
    }

    @Operation(summary = "Deletar uma proposta")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description =
                    "Ordem não encontrada.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Detalhes da ordem.")
    })
    @DeleteMapping("/{proposalId}")
    public ResponseEntity<Void> updateOrder(@PathVariable Integer proposalId) {
        proposalsService.deleteProposal(proposalId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Editar status de uma proposta")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description =
                    "Ordem não encontrada.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Status editado.")
    })
    @PatchMapping("/status/{proposalId}")
    public ResponseEntity<Void> updateProposal(@PathVariable Integer proposalId, @RequestParam EStatus status) {
        return ResponseEntity.ok(proposalsService.updateStatusProposal(proposalId, status));
    }
}
