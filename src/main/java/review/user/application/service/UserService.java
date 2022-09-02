package review.user.application.service;

import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.user.domain.entity.User;

public interface UserService {

    void create(User user);

    void update(Integer id, User updateUser);

    Pagination<User> getAll(Pageable pageable);

    User getById(Integer id);

    void delete(int id);
}
