package review.userproduct.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserProduct {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Long amount;
    private Status status;
    private LocalDateTime createdAt;

    public UserProduct(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public enum Status {
        INITIAL,
        APPROVED,
        UN_APPROVED
    }
}
