package review.comment.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import review.comment.domain.entity.Comment;
import review.comment.domain.repository.CommentRepository;
import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SimpleJpaCommentRepository implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public void save(Comment comment) {
        jpaCommentRepository.save(CommentEntity.of(comment));
    }

    @Override
    public Pagination<Comment> findAllByProductIdAndApprovedStateOrderByCreatedAtDesc(Integer productId, Pageable pageable) {
        var pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        var pageOfComments = jpaCommentRepository.findAllByProductIdAndStateIsApprovedOrderByCreatedAtDesc(productId, pageRequest);
        var comments = pageOfComments.map(CommentEntity::toComment).toList();
        return new Pagination<>(pageOfComments.getTotalElements(), pageOfComments.hasNext(), pageOfComments.hasPrevious(), comments);
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        var comment = jpaCommentRepository.findById(id);
        if (comment.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(comment.get().toComment());
    }

    @Override
    public void delete(Comment comment) {
        jpaCommentRepository.delete(CommentEntity.of(comment));
    }

    @Override
    public List<Comment> findLastThreeCommentsOfProduct(Integer productId) {
        var pageRequest = PageRequest.of(0, 3);
        return jpaCommentRepository.findLastThreeCommentsOfProduct(productId, pageRequest).map(CommentEntity::toComment).toList();
    }

    @Override
    public Integer getCountOfCommentsOfProduct(Integer productId) {
        return jpaCommentRepository.countAllByProductIdAndStateIsApproved(productId);
    }
}
