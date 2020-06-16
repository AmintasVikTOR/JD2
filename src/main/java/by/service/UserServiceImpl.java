package by.service;

import by.dao.UserDao;
import by.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(@Qualifier("userRepositoryJdbcTemplate") UserDao userDao) {
        this.userDao = userDao;
    }

    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public int delete(User user) {
        return userDao.delete(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> search(String searchParam) {
        return userDao.search(searchParam);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userDao.findById(userId);
    }
}


