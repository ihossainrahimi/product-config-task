package review.product.application.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import review.comment.application.web.CommentDto;
import review.product.domain.entity.Product;
import review.product.domain.entity.Product.CommentAuthority;
import review.product.domain.entity.Product.VoteAuthority;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static lombok.AccessLevel.PROTECTED;

@JsonAutoDetect(fieldVisibility = ANY)
@AllArgsConstructor(access = PROTECTED)
public class ProductDto {

    private Integer id;
    private String title;
    private String description;
    private Boolean visible;
    private CommentAuthority commentAuthority;
    private VoteAuthority voteAuthority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Float averageScore;
    private Integer countOfComments;
    private List<CommentDto> comments;

    public ProductDto(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    static ProductDto of(Product product, List<CommentDto> comments, Float averageScore, Integer countOfComments) {
        return new ProductDto(product.getId(), product.getTitle(), product.getDescription(), product.getVisible(),
                product.getCommentAuthority(), product.getVoteAuthority(), product.getCreatedAt(), product.getUpdatedAt(),
                averageScore, countOfComments, comments);
    }

    static ProductDto shortInfo(Product product) {
        return new ProductDto(product.getId(), product.getTitle());
    }
}
