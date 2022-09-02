package review.userproduct.domain.repository;

import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.userproduct.domain.entity.UserProduct;

import java.util.Optional;

public interface UserProductRepository {

    void save(UserProduct userProduct);

    Pagination<UserProduct> findAllByUserId(Pageable pageable);

    Optional<UserProduct> findById(Integer id);

    Optional<UserProduct> getByUserIdAndProductIdWhereStatusIsApproved(Integer userId, Integer productId);
}
