package freela.bff.domain.model.request.order;

import java.util.ArrayList;
public class CreateOrderRequest {
    private String description;
    private String title;
    private Double value;
    private ArrayList<Integer> subCategoriesIds;
    private String deadline;
    private Integer userId;
}
