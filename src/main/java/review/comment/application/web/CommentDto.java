package review.comment.application.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import review.comment.domain.entity.Comment;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static lombok.AccessLevel.PROTECTED;

@JsonAutoDetect(fieldVisibility = ANY)
@AllArgsConstructor(access = PROTECTED)
public class CommentDto {

    private Integer id;
    private Integer userId;
    private String userName;
    private String description;
    private LocalDateTime createdAt;

    public CommentDto(String userName, String description, LocalDateTime createdAt) {
        this.userName = userName;
        this.description = description;
        this.createdAt = createdAt;
    }

    static CommentDto of(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getUserId(),
                comment.getUserName(),
                comment.getDescription(),
                comment.getCreatedAt());
    }

    public static CommentDto shortInfo(Comment comment) {
        return new CommentDto(comment.getUserName(), comment.getDescription(), comment.getCreatedAt());
    }
}
