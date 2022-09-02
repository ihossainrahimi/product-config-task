package review.vote.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JpaVoteRepository extends PagingAndSortingRepository<VoteEntity, Integer> {

    @Query("select c from vote c where c.productId = :productId and c.state = 'APPROVED'")
    Page<VoteEntity> findAllByProductIdAndStateIsApproved(Integer productId, Pageable pageable);

    @Query("select c.score from vote c where c.productId = :productId and c.state = 'APPROVED'")
    List<Float> findAllScoresOfProductWithApprovedState(Integer productId);
}
