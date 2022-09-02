package review.comment.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Comment {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private String userName;
    private State state;
    private String description;
    private LocalDateTime createdAt;

    public Comment(Integer userId, Integer productId, String description) {
        this.userId = userId;
        this.productId = productId;
        this.description = description;
    }

    public enum State {
        INIT,
        APPROVED,
        UN_APPROVED
    }
}

