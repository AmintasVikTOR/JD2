package by.dao;

import by.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    /*CRUD operations*/
    /*Create = Insert*/
    /*Read = select*/
    /*Update*/
    /*Delete*/
    List<User> findAll();

    List<User> search(String searchParam);

    Optional<User> findById(long userId);

    User findOne(long userId);

    User save(User user);

    User update(User user);

    int delete(User user);
}
