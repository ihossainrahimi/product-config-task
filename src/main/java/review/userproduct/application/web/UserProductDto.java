package review.userproduct.application.web;

import review.userproduct.domain.entity.UserProduct;
import review.userproduct.domain.entity.UserProduct.Status;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static lombok.AccessLevel.PROTECTED;

@JsonAutoDetect(fieldVisibility = ANY)
@AllArgsConstructor(access = PROTECTED)
public class UserProductDto {

    private Integer id;
    private Long amount;
    private Status status;
    private LocalDateTime createdAt;


    public UserProductDto(Integer id, Long amount, Status status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }

    public static UserProductDto shortInfo(UserProduct userProduct) {
        return new UserProductDto(userProduct.getId(), userProduct.getAmount(), userProduct.getStatus());
    }

    public static UserProductDto of(UserProduct userProduct) {
        return new UserProductDto(
                userProduct.getId(),
                userProduct.getAmount(),
                userProduct.getStatus(),
                userProduct.getCreatedAt());
    }
}
