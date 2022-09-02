package review.userproduct.application.web;

import review.userproduct.domain.entity.UserProduct;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class UserProductCreationDto {

    @NotNull(message = "UserProductCreationUserIdIsRequired")
    private Integer userId;
    @NotNull(message = "UserProductCreationProductIdIsRequired")
    private Integer productId;

    UserProduct toUserProduct() {
        return new UserProduct(userId, productId);
    }
}
