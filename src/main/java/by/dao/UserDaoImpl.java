package by.dao;

import by.domain.User;
import by.util.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.util.DatabaseConfiguration.*;

public class UserDaoImpl implements UserDao {

    public static DatabaseConfiguration config = DatabaseConfiguration.getInstance();

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

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from m_users order by id desc";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        /*1. Load driver*/
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        List<User> resultList = new ArrayList<>();
        /*2. DriverManager should get connection*/
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
                /*3. Get statement from connection*/
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {

            /*4. Execute query*/
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                /*6. Add parsed info into collection*/
                resultList.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultList;
    }

    @Override
    public List<User> search(String searchParam) {
        return null;
    }

    @Override
    public Optional<User> findById(long userId) {
        return Optional.empty();
    }

    @Override
    public User findOne(long userId) {
        final String findAllQuery = "select * from m_users order by id desc";

        String driverName = config.getProperty(DATABASE_DRIVER_NAME);
        String url = config.getProperty(DATABASE_URL);
        String login = config.getProperty(DATABASE_LOGIN);
        String databasePassword = config.getProperty(DATABASE_PASSWORD);

        /*1. Load driver*/
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Don't worry:)");
        }

        List<User> resultList = new ArrayList<>();
        /*2. DriverManager should get connection*/
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
                /*3. Get statement from connection*/
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {

            /*4. Execute query*/
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                /*6. Add parsed info into collection*/
                resultList.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
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

    private User parseResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        /*5. Columns mapping*/
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
}

