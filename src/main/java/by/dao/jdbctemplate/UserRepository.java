package by.dao.jdbctemplate;

import by.dao.UserDao;
import by.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("userRepositoryJdbcTemplate")

public class UserRepository implements UserDao {

    public static final String USER_ID = "id";
    public static final String USER_USERNAME = "username";
    public static final String USER_SURNAME = "surname";
    public static final String USER_BIRTH_DATE = "birth_date";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CREATED = "created";
    public static final String USER_CHANGED = "changed";
    public static final String USER_IS_BLOCKED = "is_blocked";
    public static final String USER_WEIGHT = "weight";

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        /*Here will be aspect logic Before*/

        final String findAllQuery = "select * from m_users order by id desc";
        /*Here will be aspect logic After*/
        return jdbcTemplate.query(findAllQuery, this::userRowMapper);
        /*Here will be aspect logic AfterReturning*/
        /*Here will be aspect logic AfterThrowing*/
    }

    private User userRowMapper(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(USER_ID));
        user.setUsername(resultSet.getString(USER_USERNAME));
        user.setSurname(resultSet.getString(USER_SURNAME));
        user.setBirthDate(resultSet.getDate(USER_BIRTH_DATE));
        user.setLogin(resultSet.getString(USER_LOGIN));
        user.setPassword(resultSet.getString(USER_PASSWORD));
        user.setCreated(resultSet.getTimestamp(USER_CREATED));
        user.setChanged(resultSet.getTimestamp(USER_CHANGED));
        user.setBlocked(resultSet.getBoolean(USER_IS_BLOCKED));
        user.setWeight(resultSet.getFloat(USER_WEIGHT));
        return user;
    }

    @Override
    public List<User> search(String searchParam) {
        final String searchQuery = "select * from m_users where login = ? order by id desc";
        /*Here will be aspect logic After*/
        return jdbcTemplate.query(searchQuery, this::userRowMapper, searchParam);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.empty();
    }

    @Override
    public User findOne(Long userId) {
        final String findById = "select * from m_users where id = ?";

        /*Here will be aspect logic After*/
        return jdbcTemplate.queryForObject(findById, this::userRowMapper, userId);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public int delete(User user) {
        return 0;
    }
}
