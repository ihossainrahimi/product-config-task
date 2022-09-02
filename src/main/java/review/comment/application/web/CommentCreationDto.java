package review.comment.application.web;

import review.comment.domain.entity.Comment;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class CommentCreationDto {

    @NotNull(message = "CommentCreationUserIdIsRequired")
    private Integer userId;
    @NotNull(message = "CommentCreationProductIdIsRequired")
    private Integer productId;
    @NotBlank(message = "CommentCreationDescriptionIsRequired")
    private String description;

    Comment toComment() {
        return new Comment(userId, productId, description);
    }
}
