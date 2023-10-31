package freela.bff.domain.model.response.user;

import freela.bff.domain.model.response.categories.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String photo;
    private String description;
    private String uf;
    private String city;
    private Boolean isFreelancer;
    private Double rate;
    private String devideId;
    private List<SubCategory> subCategories;
}
