package review.comment.application.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import review.comment.domain.entity.Comment;
import review.comment.domain.repository.CommentRepository;
import review.product.application.service.ProductService;
import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.user.application.service.UserService;
import review.userproduct.application.service.UserProductNotFoundException;
import review.userproduct.application.service.UserProductService;

import java.util.List;

import static review.product.domain.entity.Product.CommentAuthority.*;

@Service
public class SimpleCommentService implements CommentService {

    private final CommentRepository commentRepository;

    private final ProductService productService;
    private final UserProductService userProductService;
    private final UserService userService;

    public SimpleCommentService(CommentRepository commentRepository,
                                @Lazy ProductService productService,
                                UserProductService userProductService,
                                UserService userService) {
        this.commentRepository = commentRepository;
        this.productService = productService;
        this.userProductService = userProductService;
        this.userService = userService;
    }

    @Override
    public void create(Comment comment) {
        validateUserCouldComment(comment.getUserId(), comment.getProductId());
        comment.setUserName(userService.getById(comment.getUserId()).getName());
        commentRepository.save(comment);
    }

    @Override
    public void update(Integer id, Comment.State state) {
        var comment = getById(id);
        comment.setState(state);
        commentRepository.save(comment);
    }

    @Override
    public Pagination<Comment> getAllByProductIdAndApprovedStateOrderByCreatedAtDesc(Integer productId, Pageable pageable) {
        return commentRepository.findAllByProductIdAndApprovedStateOrderByCreatedAtDesc(productId, pageable);
    }

    @Override
    public List<Comment> getLastThreeCommentsOfProduct(Integer productId) {
        return commentRepository.findLastThreeCommentsOfProduct(productId);
    }

    @Override
    public Integer getCountOfCommentsOfProduct(Integer productId) {
        return commentRepository.getCountOfCommentsOfProduct(productId);
    }

    @Override
    public Comment getById(Integer id) {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public void delete(Integer id) {
        var comment = getById(id);
        commentRepository.delete(comment);
    }

    private void validateUserCouldComment(Integer userId, Integer productId) {
        var product = productService.getById(productId);
        if (product.getCommentAuthority() == ALL) {
            return;
        }
        if (product.getCommentAuthority() == NO_BODY) {
            throw new ProductIsNotCommentableException();
        }
        if (product.getCommentAuthority() == ONLY_BUYERS) {
            try {
                userProductService.getByUserIdAndProductIdWhereStatusIsApproved(userId, productId);
            } catch (UserProductNotFoundException e) {
                throw new OnlyProductBuyersCouldCommentException();
            }
        }
    }
}
