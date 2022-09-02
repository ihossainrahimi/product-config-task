package review.vote.application.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import review.product.application.service.ProductService;
import review.product.domain.entity.Product;
import review.userproduct.application.service.UserProductNotFoundException;
import review.userproduct.application.service.UserProductService;
import review.vote.domain.entity.Vote;
import review.vote.domain.repository.VoteRepository;

import java.util.List;

import static review.product.domain.entity.Product.VoteAuthority.*;

@Service
public class SimpleVoteService implements VoteService {

    public SimpleVoteService(VoteRepository voteRepository,
                             @Lazy ProductService productService,
                             UserProductService userProductService) {
        this.voteRepository = voteRepository;
        this.productService = productService;
        this.userProductService = userProductService;
    }

    private final VoteRepository voteRepository;
    private final ProductService productService;
    private final UserProductService userProductService;

    @Override
    public void create(Vote vote) {
        validateUserCouldVote(vote.getUserId(), vote.getProductId());
        voteRepository.save(vote);
    }

    @Override
    public void update(Integer id, Vote.State state) {
        var vote = getById(id);
        vote.setState(state);
        voteRepository.save(vote);
    }

    @Override
    public List<Float> getAllScoresOfProductWithApprovedState(Integer productId) {
        return voteRepository.findAllScoresOfProductWithApprovedState(productId);
    }

    @Override
    public Vote getById(Integer id) {
        return voteRepository.findById(id).orElseThrow(VoteNotFoundException::new);
    }

    @Override
    public void delete(Integer id) {
        var vote = getById(id);
        voteRepository.delete(vote);
    }

    private void validateUserCouldVote(Integer userId, Integer productId) {
        var product = productService.getById(productId);
        if (product.getVoteAuthority() == ALL) {
            return;
        }
        if (product.getVoteAuthority() == NO_BODY) {
            throw new ProductIsNotVotable();
        }
        if (product.getVoteAuthority() == ONLY_BUYERS) {
            try {
                userProductService.getByUserIdAndProductIdWhereStatusIsApproved(userId, productId);
            } catch (UserProductNotFoundException e) {
                throw new OnlyProductBuyersCouldVoteException();
            }
        }
    }
}
