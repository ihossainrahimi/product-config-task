package review.product.infrastructure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import review.comment.infrastructure.CommentEntity;
import review.product.domain.entity.Product;
import review.vote.infrastructure.VoteEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity(name = "product")
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id=?")
@Where(clause = "deleted = 'false'")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Boolean visible;
    @Enumerated(EnumType.STRING)
    private Product.CommentAuthority commentAuthority;
    @Enumerated(EnumType.STRING)
    private Product.VoteAuthority voteAuthority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;

    @OneToMany(mappedBy = "product")
    private Set<CommentEntity> comments;

    @OneToMany(mappedBy = "product")
    private Set<VoteEntity> votes;

    public ProductEntity(Integer id, String title, String description, Boolean visible,
                         Product.CommentAuthority commentAuthority, Product.VoteAuthority voteAuthority,
                         LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.visible = visible;
        this.commentAuthority = commentAuthority;
        this.voteAuthority = voteAuthority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.deleted = false;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    static ProductEntity of(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getVisible(),
                product.getCommentAuthority(),
                product.getVoteAuthority(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getDeleted());
    }

    Product toProduct() {
        return new Product(id, title, description, visible, commentAuthority, voteAuthority, createdAt, updatedAt, deleted);
    }

}
