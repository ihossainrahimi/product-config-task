package review.comment.domain.repository;

import review.comment.domain.entity.Comment;
import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    void save(Comment comment);

    Pagination<Comment> findAllByProductIdAndApprovedStateOrderByCreatedAtDesc(Integer productId, Pageable pageable);

    Optional<Comment> findById(Integer id);

    void delete(Comment comment);

    List<Comment> findLastThreeCommentsOfProduct(Integer productId);

    Integer getCountOfCommentsOfProduct(Integer productId);
}
