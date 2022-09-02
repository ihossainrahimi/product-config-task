package review.product.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import review.comment.application.service.CommentService;
import review.comment.domain.entity.Comment;
import review.product.domain.entity.Product;
import review.product.domain.repository.ProductRepository;
import review.vote.application.service.VoteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CommentService commentService;
    private final VoteService voteService;

    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Integer id, Product updateProduct) {
        var product = getById(id);
        product.update(updateProduct);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllWhereVisibleIsTrueAndDeletedAtIsFalse() {
        return productRepository.findAllWhereVisibleIsTrueAndDeletedIsFalse();
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void delete(Integer id) {
        var product = getById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Comment> getLastThreeCommentsOfProduct(Integer productId) {
        return commentService.getLastThreeCommentsOfProduct(productId);
    }

    @Override
    public Integer getCountOfCommentsOfProduct(Integer productId){
        return commentService.getCountOfCommentsOfProduct(productId);
    }

    @Override
    public Float getAverageScoreOfProduct(Integer productId) {
        var scores = voteService.getAllScoresOfProductWithApprovedState(productId);
        float sumOfScores = 0;
        if (!scores.isEmpty()) {
            for (Float score : scores) {
                sumOfScores += score;
            }
            return sumOfScores / scores.size();
        }
        return 0F;
    }
}
