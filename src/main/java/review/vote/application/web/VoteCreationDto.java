package review.vote.application.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import review.vote.domain.entity.Vote;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class VoteCreationDto {

    @NotNull(message = "VoteCreationUserIdIsRequired")
    private Integer userId;
    @NotNull(message = "VoteCreationProductIdIsRequired")
    private Integer productId;
    @NotNull(message = "VoteCreationScoreIsRequired")
    private Float score;

    Vote toVote() {
        return new Vote(userId, productId, score);
    }
}
