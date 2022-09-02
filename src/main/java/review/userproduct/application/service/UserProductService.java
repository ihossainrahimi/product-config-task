package review.userproduct.application.service;

import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.userproduct.domain.entity.UserProduct;
import review.userproduct.domain.entity.UserProduct.Status;

public interface UserProductService {

    void create(UserProduct userProduct);

    Pagination<UserProduct> getAllByUserId(Pageable pageable);

    UserProduct getById(Integer id);

    void update(Integer id, Status status);

    UserProduct getByUserIdAndProductIdWhereStatusIsApproved(Integer userId, Integer productId);
}
