package freela.bff.web.controller;

import freela.bff.domain.model.response.proposal.CreateProposalRequest;
import freela.bff.domain.model.response.proposal.Proposal;
import freela.bff.domain.service.ProposalService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @ApiResponses({
            @ApiResponse(responseCode = "400", description =
                    "Pedido ja aceito.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "201", description = "Criado!.")
    })
    @PostMapping("/{orderId}/{userId}")
    public ResponseEntity<Proposal> post(
            @PathVariable @NotNull int orderId,
            @PathVariable @NotNull int userId,
            @RequestBody CreateProposalRequest request
    ){
        return ResponseEntity.ok(proposalService.create(userId,request,orderId));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "400", description =
                    "Pedido ja aceito.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "201", description = "Criado!.")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<Proposal[]> findProposalsByUserid(@PathVariable Integer userId) {
        return ResponseEntity.ok(proposalService.findProposalsByUserId(userId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Proposal> findProposalsByOrder(@PathVariable Integer orderId){
        return ResponseEntity.ok(proposalService.findProposalsByOrder(orderId));
    }
}
