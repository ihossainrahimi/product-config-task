package review.vote.application.service;

import review.vote.domain.entity.Vote;

import java.util.List;

public interface VoteService {

    void create(Vote vote);

    void update(Integer id, Vote.State state);

    List<Float> getAllScoresOfProductWithApprovedState(Integer productId);

    Vote getById(Integer id);

    void delete(Integer id);
}
