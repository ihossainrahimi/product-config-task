package review.vote.infrastructure;

import lombok.NoArgsConstructor;
import review.product.infrastructure.ProductEntity;
import review.user.infrastructure.UserEntity;
import review.vote.domain.entity.Vote;
import review.vote.domain.entity.Vote.State;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static review.vote.domain.entity.Vote.State.INIT;

@Entity(name = "vote")
@NoArgsConstructor(access = PROTECTED)
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Float score;
    @Enumerated(EnumType.STRING)
    private State state;
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", updatable = false, insertable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private ProductEntity product;

    static VoteEntity of(Vote vote) {
        return new VoteEntity(
                vote.getId(),
                vote.getUserId(),
                vote.getProductId(),
                vote.getScore(),
                vote.getState(),
                vote.getCreatedAt());
    }

    public Vote toVote() {
        return new Vote(id, userId, productId, score, state, createdAt);
    }

    public VoteEntity(Integer id, Integer userId, Integer productId, Float score, State state, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.score = score;
        this.state = state;
        this.createdAt = createdAt;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.state = INIT;
    }
}
