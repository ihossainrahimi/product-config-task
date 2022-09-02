package review.vote.application.web;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import review.vote.domain.entity.Vote.State;
import review.vote.application.service.VoteService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static review.shared.web.UrlMappings.*;

@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping(VOTE)
    @ResponseStatus(CREATED)
    public void create(@RequestBody @Validated VoteCreationDto dto) {
        voteService.create(dto.toVote());
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping(UPDATE_VOTE)
    public void update(@PathVariable Integer id, @RequestParam State state) {
        voteService.update(id, state);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(DELETE_VOTE)
    public void delete(@PathVariable Integer id) {
        voteService.delete(id);
    }
}
