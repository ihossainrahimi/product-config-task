package review.product.application.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import review.product.domain.entity.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class ProductUpdateDto {

    @NotBlank(message = "ProductUpdateTitleIsRequired")
    private String title;

    private String description;

    @NotNull(message = "ProductUpdateVisibilityIsRequired")
    private Boolean visible;

    @NotNull(message = "ProductUpdateCommentAuthorityIsRequired")
    private Product.CommentAuthority commentAuthority;

    @NotNull(message = "ProductUpdateVoteAuthorityIsRequired")
    private Product.VoteAuthority voteAuthority;

    Product toProduct() {
        return new Product(title, description, visible, commentAuthority, voteAuthority);
    }
}
