package by.service;

import by.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    List<User> search(String searchParam);

    Optional<User> findById(Long userId);

    User findOne(Long userId);

    User save(User user);

    User update(User user);

    int delete(User user);
}
