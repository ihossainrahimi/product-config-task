package review.user.domain.repository;

import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Pagination<User> findAll(Pageable pageable);

    Optional<User> findById(Integer id);

    void delete(User user);
}
