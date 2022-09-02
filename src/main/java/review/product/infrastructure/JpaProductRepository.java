package review.product.infrastructure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import review.comment.infrastructure.CommentEntity;
import review.vote.infrastructure.VoteEntity;

import java.util.List;
import java.util.Optional;

public interface JpaProductRepository extends PagingAndSortingRepository<ProductEntity, Integer> {

    @Query("select p from product p where p.visible = true")
    List<ProductEntity> findAllWhereVisibleIsTrueAndDeletedIsFalse();
}
