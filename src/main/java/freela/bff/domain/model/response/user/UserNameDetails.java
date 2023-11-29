package freela.bff.domain.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNameDetails {
    private Integer id;
    private String name;
    private String photo;

    public UserNameDetails(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.photo = user.getPhoto();
    }
}
