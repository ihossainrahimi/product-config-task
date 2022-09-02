package review.comment.infrastructure;

import lombok.NoArgsConstructor;
import review.comment.domain.entity.Comment;
import review.product.infrastructure.ProductEntity;
import review.user.infrastructure.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static review.comment.domain.entity.Comment.State.INITIAL;

@Entity(name = "comment")
@NoArgsConstructor(access = PROTECTED)
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer productId;
    private String userName;
    @Enumerated(EnumType.STRING)
    private Comment.State state;
    private String description;
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", updatable = false, insertable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private ProductEntity product;

    static CommentEntity of(Comment comment) {
        return new CommentEntity(
                comment.getId(),
                comment.getUserId(),
                comment.getProductId(),
                comment.getUserName(),
                comment.getState(),
                comment.getDescription(),
                comment.getCreatedAt());
    }

    public Comment toComment() {
        return new Comment(id, userId, productId, userName, state, description, createdAt);
    }

    public CommentEntity(Integer id, Integer userId, Integer productId, String userName, Comment.State state,
                         String description, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.userName = userName;
        this.state = state;
        this.description = description;
        this.createdAt = createdAt;
    }

    @PrePersist
    private void prePersist() {
        createdAt = LocalDateTime.now();
        state = INITIAL;
    }
}
