package review.vote.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Vote {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Float score;
    private State state;
    private LocalDateTime createdAt;

    public Vote(Integer userId, Integer productId, Float score) {
        this.userId = userId;
        this.productId = productId;
        this.score = score;
    }

    public Vote(State state) {
        this.state = state;
    }

    public enum State {
        INITIAL,
        APPROVED,
        UN_APPROVED
    }

}
