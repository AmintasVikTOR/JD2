package by.dao.jdbc;

import by.dao.UserDao;
import by.domain.User;
import by.exceptions.ResourceNotFoundException;
import by.util.DatabaseConfiguration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.util.DatabaseConfiguration.*;

@Repository("userRepositoryJdbc")
public class UserDaoImpl implements UserDao {
    public static DatabaseConfiguration config = DatabaseConfiguration.getInstance();

    // log4j
    private static final Logger log = Logger.getLogger(UserDaoImpl.class);

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

    private DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserDaoImpl() {

    }

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

            // log4j
            log.debug("Getting connection");
            log.debug("Prepared statement creation");

            /*4. Execute query*/
            ResultSet resultSet = preparedStatement.executeQuery();
            log.debug("Executing query");
            while (resultSet.next()) {
                log.debug("Parsing result set");
                /*6. Add parsed info into collection*/
                resultList.add(parseResultSet(resultSet));
            }
            log.debug("Successfully parsed result set");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            log.error("Find all method error!", e);
        }

        return resultList;
    }

    @Override
    public List<User> search(String searchParam) {
        final String findAllQueryForPrepared = "select * from m_users where id > ? order by id desc"; //:{имя параметра}    ?

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
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQueryForPrepared)) {

            preparedStatement.setLong(1, Long.parseLong(searchParam));

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
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(findOne(userId));
    }

    @Override
    public User findOne(Long userId) {
        final String findById = "select * from m_users where id = ?";

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

        User user = null;
        ResultSet resultSet = null;
        /*2. DriverManager should get connection*/
        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
                /*3. Get statement from connection*/
             PreparedStatement preparedStatement = connection.prepareStatement(findById);
        ) {

            preparedStatement.setLong(1, userId);
            /*4. Execute query*/
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                /*6. Add parsed info into collection*/
                user = parseResultSet(resultSet);
            } else {
                throw new ResourceNotFoundException("User with id " + userId + " not found");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }

        return user;
    }

    @Override
    public User save(User user) {

        final String insertQuery = "INSERT INTO m_users (username, surname, birth_date, login, password, created, changed, weight, is_blocked)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
                /*3. Get statement from connection*/
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             PreparedStatement lastInsertId = connection.prepareStatement("SELECT currval('m_users_id_seq') as last_insert_id;");
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setDate(3, user.getBirthDate());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setTimestamp(6, user.getCreated());
            preparedStatement.setTimestamp(7, user.getChanged());
            preparedStatement.setDouble(8, user.getWeight());
            preparedStatement.setBoolean(9, user.isBlocked());

            preparedStatement.executeUpdate();

            ResultSet set = lastInsertId.executeQuery();
            set.next();
            long insertedUserId = set.getInt("last_insert_id");
            return findOne(insertedUserId);

        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update m_users set username = ?, surname = ?, birth_date = ?, login = ?, password = ?, " +
                "created = ?, changed = ?, weight = ?, is_blocked = ? " +
                "where id = ?";

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

        try (Connection connection = DriverManager.getConnection(url, login, databasePassword);
                /*3. Get statement from connection*/
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setDate(3, user.getBirthDate());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setTimestamp(6, user.getCreated());
            preparedStatement.setTimestamp(7, user.getChanged());
            preparedStatement.setDouble(8, user.getWeight());
            preparedStatement.setBoolean(9, user.isBlocked());

            preparedStatement.setLong(10, user.getId());

            preparedStatement.executeUpdate();

            return findOne(user.getId());

        } catch (SQLException e) {
            throw new RuntimeException("Some issues in insert operation!", e);
        }
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
