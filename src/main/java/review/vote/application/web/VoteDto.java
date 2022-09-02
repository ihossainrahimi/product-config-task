package review.vote.application.web;

import review.vote.domain.entity.Vote;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static lombok.AccessLevel.PROTECTED;

@JsonAutoDetect(fieldVisibility = ANY)
@AllArgsConstructor(access = PROTECTED)
public class VoteDto {

    private int id;
    private int userId;
    private int productId;
    private Float score;
    private Vote.State state;
    private LocalDateTime createdAt;

    Vote toVote() {
        return new Vote(id, userId, productId, score, state, createdAt);
    }

    static VoteDto of(Vote vote) {
        return new VoteDto(vote.getId(), vote.getUserId(), vote.getProductId(), vote.getScore(), vote.getState(), vote.getCreatedAt());
    }
}
