package review.user.infrastructure;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface JpaUserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
}
