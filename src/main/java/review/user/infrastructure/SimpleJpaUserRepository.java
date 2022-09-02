package review.user.infrastructure;

import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.user.domain.entity.User;
import review.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SimpleJpaUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;


    @Override
    public void save(User user) {
        jpaUserRepository.save(UserEntity.of(user));
    }

    @Override
    public Pagination<User> findAll(Pageable pageable) {
        var pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        var pageOfUsers = jpaUserRepository.findAll(pageRequest);
        var users = pageOfUsers.map(UserEntity::toUser).toList();
        return new Pagination<>(pageOfUsers.getTotalElements(), pageOfUsers.hasNext(), pageOfUsers.hasPrevious(), users);
    }

    @Override
    public Optional<User> findById(Integer id) {
        var user = jpaUserRepository.findById(id);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(user.get().toUser());
    }

    @Override
    public void delete(User user) {
        jpaUserRepository.delete(UserEntity.of(user));
    }
}
