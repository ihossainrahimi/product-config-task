package review.product.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import review.comment.domain.entity.Comment;
import review.comment.infrastructure.CommentEntity;
import review.product.domain.entity.Product;
import review.product.domain.repository.ProductRepository;
import review.vote.domain.entity.Vote;
import review.vote.infrastructure.VoteEntity;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SimpleJpaProductRepository implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;


    @Override
    public void save(Product product) {
        jpaProductRepository.save(ProductEntity.of(product));
    }

    @Override
    public List<Product> findAllWhereVisibleIsTrueAndDeletedIsFalse() {
        return jpaProductRepository.findAllWhereVisibleIsTrueAndDeletedIsFalse().stream().map(ProductEntity::toProduct).toList();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        var product = jpaProductRepository.findById(id);
        if (product.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(product.get().toProduct());
    }

    @Override
    public void delete(Product product) {
        jpaProductRepository.delete(ProductEntity.of(product));
    }
}
