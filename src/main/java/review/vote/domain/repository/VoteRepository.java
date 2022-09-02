package review.vote.domain.repository;

import review.vote.domain.entity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteRepository {

    void save(Vote vote);

    List<Float> findAllScoresOfProductWithApprovedState(Integer productId);

    Optional<Vote> findById(Integer id);

    void delete(Vote vote);
}
