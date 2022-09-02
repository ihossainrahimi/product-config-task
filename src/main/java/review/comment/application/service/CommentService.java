package review.comment.application.service;

import review.comment.domain.entity.Comment;
import review.comment.domain.entity.Comment.State;
import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;

import java.util.List;

public interface CommentService {

    void create(Comment comment);

    void update(Integer id, State state);

    Pagination<Comment> getAllByProductIdAndApprovedStateOrderByCreatedAtDesc(Integer productId, Pageable pageable);

    List<Comment> getLastThreeCommentsOfProduct(Integer productId);

    Comment getById(Integer id);

    void delete(Integer id);

    Integer getCountOfCommentsOfProduct(Integer productId);
}
