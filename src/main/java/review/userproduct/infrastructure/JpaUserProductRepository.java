package review.userproduct.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaUserProductRepository extends PagingAndSortingRepository<UserProductEntity, Integer> {

    Page<UserProductEntity> getAllByUserId(Integer userId, Pageable pageable);

    @Query("select up from user_product up where up.userId = :userId and up.productId = :productId and up.status = 'APPROVED'")
    Optional<UserProductEntity> getByUserIdAndProductIdWhereStatusIsApproved(@Param("userId") Integer userId, @Param("productId") Integer productId);
}
