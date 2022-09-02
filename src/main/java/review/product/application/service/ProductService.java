package review.product.application.service;

import review.comment.domain.entity.Comment;
import review.product.domain.entity.Product;

import java.util.List;

public interface ProductService {

    void create(Product product);

    void update(Integer id, Product updateProduct);

    List<Product> getAllWhereVisibleIsTrueAndDeletedAtIsFalse();

    Product getById(Integer id);

    List<Comment> getLastThreeCommentsOfProduct(Integer productId);

    Float getAverageScoreOfProduct(Integer productId);

    void delete(Integer id);

    Integer getCountOfCommentsOfProduct(Integer productId);
}
