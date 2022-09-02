package review.product.application.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import review.product.domain.entity.Product;
import review.product.domain.entity.Product.CommentAuthority;
import review.product.domain.entity.Product.VoteAuthority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class ProductCreationDto {

    @NotBlank(message = "ProductCreationTitleIsRequired")
    private String title;

    private String description;
    private Boolean visible;

    @NotNull(message = "ProductCreationCommentAuthorityIsRequired")
    private CommentAuthority commentAuthority;

    @NotNull(message = "ProductCreationVoteAuthorityIsRequired")
    private VoteAuthority voteAuthority;


    Product toProduct() {
        return new Product(title, description, visible, commentAuthority, voteAuthority);
    }
}
