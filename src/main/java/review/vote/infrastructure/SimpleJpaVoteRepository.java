package review.vote.infrastructure;

import review.vote.domain.entity.Vote;
import review.vote.domain.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SimpleJpaVoteRepository implements VoteRepository {

    private final JpaVoteRepository jpaVoteRepository;

    @Override
    public void save(Vote vote) {
        jpaVoteRepository.save(VoteEntity.of(vote));
    }

    @Override
    public List<Float> findAllScoresOfProductWithApprovedState(Integer productId) {
        return jpaVoteRepository.findAllScoresOfProductWithApprovedState(productId);
    }

    @Override
    public Optional<Vote> findById(Integer id) {
        var vote = jpaVoteRepository.findById(id);
        if (vote.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(vote.get().toVote());
    }

    @Override
    public void delete(Vote vote) {
        jpaVoteRepository.delete(VoteEntity.of(vote));
    }
}
