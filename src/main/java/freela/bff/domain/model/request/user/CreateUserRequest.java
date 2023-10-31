package freela.bff.domain.model.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @Size(min = 2, max = 50)
    private String name;
    @Email
    private String email;
    @Size(min = 8, max = 50)
    private String password;
    @NotNull
    @NotEmpty
    private ArrayList<Integer> subCategoriesIds;
    private String city;
    private String uf;
    private Boolean isFreelancer;
    private String devideId;
}