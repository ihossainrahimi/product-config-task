package review.user.application.service;

import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.user.domain.entity.User;
import review.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(Integer id, User updateUser) {
        var user = getById(id);
        user.update(updateUser);
        userRepository.save(user);
    }

    @Override
    public Pagination<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void delete(int id) {
        var user = getById(id);
        userRepository.delete(user);
    }
}
