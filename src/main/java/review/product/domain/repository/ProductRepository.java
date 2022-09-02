package review.product.domain.repository;

import review.comment.domain.entity.Comment;
import review.product.domain.entity.Product;
import review.vote.domain.entity.Vote;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);

    List<Product> findAllWhereVisibleIsTrueAndDeletedIsFalse();

    Optional<Product> findById(Integer id);

    void delete(Product product);
}
