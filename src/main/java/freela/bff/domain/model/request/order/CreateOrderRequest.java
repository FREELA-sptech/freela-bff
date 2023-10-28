package freela.bff.domain.model.request.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private String description;
    private String title;
    private Double value;
    private ArrayList<Integer> subCategoriesIds;
    private String deadline;
    @Schema(hidden = true)
    private Integer userId;
}
