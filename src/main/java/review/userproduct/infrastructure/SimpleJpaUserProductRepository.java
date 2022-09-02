package review.userproduct.infrastructure;

import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.userproduct.domain.entity.UserProduct;
import review.userproduct.domain.repository.UserProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SimpleJpaUserProductRepository implements UserProductRepository {

    private static final Integer AUTH_USER_ID = 1;

    private final JpaUserProductRepository jpaUserProductRepository;

    @Override
    public void save(UserProduct userProduct) {
        jpaUserProductRepository.save(UserProductEntity.of(userProduct));
    }

    @Override
    public Pagination<UserProduct> findAllByUserId(Pageable pageable) {
        var pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        var pageOfUserProducts = jpaUserProductRepository.getAllByUserId(AUTH_USER_ID, pageRequest);
        var userProducts = pageOfUserProducts.map(UserProductEntity::toUserProduct).toList();
        return new Pagination<>(
                pageOfUserProducts.getTotalElements(),
                pageOfUserProducts.hasNext(),
                pageOfUserProducts.hasPrevious(),
                userProducts);
    }

    @Override
    public Optional<UserProduct> findById(Integer id) {
        var userProduct = jpaUserProductRepository.findById(id);
        if (userProduct.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userProduct.get().toUserProduct());
    }

    @Override
    public Optional<UserProduct> getByUserIdAndProductIdWhereStatusIsApproved(Integer userId, Integer productId) {
        var userProduct = jpaUserProductRepository.getByUserIdAndProductIdWhereStatusIsApproved(userId, productId);
        if (userProduct.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userProduct.get().toUserProduct());
    }
}
