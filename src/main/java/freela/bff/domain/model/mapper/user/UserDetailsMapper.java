package freela.bff.domain.model.mapper.user;

import freela.bff.domain.model.response.user.User;
import freela.bff.domain.model.response.user.UserDetailsResponse;

import java.util.Arrays;

public class UserDetailsMapper {
    public static UserDetailsResponse map(User user) {
        return UserDetailsResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .photo(user.getPhoto())
                .description(user.getDescription())
                .rate(user.getRate())
                .uf(user.getUf())
                .city(user.getCity())
                .subCategories(user.getSubCategories())
                .isFreelancer(user.getIsFreelancer())
                .build();
    }

    public static UserDetailsResponse[] mapper(User[] users) {
        return Arrays.stream(users)
                .map(UserDetailsMapper::map)
                .toArray(UserDetailsResponse[]::new);
    }
}
