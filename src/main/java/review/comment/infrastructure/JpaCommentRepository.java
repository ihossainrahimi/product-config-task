package review.comment.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface JpaCommentRepository extends PagingAndSortingRepository<CommentEntity, Integer> {

    @Query("select c from comment c where c.productId = :productId and c.state = 'APPROVED' order by c.createdAt desc")
    Page<CommentEntity> findAllByProductIdAndStateIsApprovedOrderByCreatedAtDesc(@Param("productId") Integer productId, Pageable pageable);

    @Query("SELECT c FROM comment c WHERE c.productId = :productId AND c.state = 'APPROVED' ORDER BY c.createdAt desc")
    Page<CommentEntity> findLastThreeCommentsOfProduct(@Param("productId") Integer productId, Pageable pageable);

    @Query("select count(c) from comment c where c.productId = :productId and c.state = 'APPROVED'")
    Integer countAllByProductIdAndStateIsApproved(@Param("productId") Integer productId);
}
