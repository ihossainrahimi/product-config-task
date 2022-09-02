package review.product.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private Integer id;
    private String title;
    private String description;
    private Boolean visible;
    private CommentAuthority commentAuthority;
    private VoteAuthority voteAuthority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;

    public Product(String title,
                   String description,
                   Boolean visible,
                   CommentAuthority commentAuthority,
                   VoteAuthority voteAuthority) {

        this.title = title;
        this.description = description;
        this.visible = visible;
        this.commentAuthority = commentAuthority;
        this.voteAuthority = voteAuthority;
    }

    public enum CommentAuthority {
        ALL,
        NO_BODY,
        ONLY_BUYERS
    }

    public enum VoteAuthority {
        ALL,
        NO_BODY,
        ONLY_BUYERS
    }

    public void update(Product product) {
        this.setTitle(product.getTitle());
        this.setDescription(product.getDescription());
        this.setVisible(product.getVisible());
        this.setCommentAuthority(product.getCommentAuthority());
        this.setVoteAuthority(product.getVoteAuthority());
    }
}
