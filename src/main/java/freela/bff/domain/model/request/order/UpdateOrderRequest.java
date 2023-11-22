package freela.bff.domain.model.request.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {
    private String description;
    private String title;
    private Double value;
    private ArrayList<Integer> subCategoriesIds;
    private String deadline;
    private ArrayList<Integer> deletedPhotos;
}
