package review.comment.application.web;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import review.comment.domain.entity.Comment.State;
import review.comment.application.service.CommentService;
import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;

import static org.springframework.http.HttpStatus.*;
import static review.shared.web.UrlMappings.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(COMMENT)
    @ResponseStatus(CREATED)
    public void create(@RequestBody @Validated CommentCreationDto dto) {
        commentService.create(dto.toComment());
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping(UPDATE_COMMENT)
    public void update(@PathVariable Integer id, @RequestParam State state) {
        commentService.update(id, state);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(DELETE_COMMENT)
    public void delete(@PathVariable Integer id) {
        commentService.delete(id);
    }

    @ResponseStatus(OK)
    @GetMapping(GET_ALL_COMMENT)
    public Pagination<CommentDto> getAllOrderByCreatedAtDesc(@PathVariable Integer productId, Pageable pageable) {
        var comments = commentService.getAllByProductIdAndApprovedStateOrderByCreatedAtDesc(productId, pageable);
        return comments.map(CommentDto::of);
    }
}
