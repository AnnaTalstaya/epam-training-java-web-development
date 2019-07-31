package by.talstaya.crackertracker.dao.user;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.dao.UserDao;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends UserDao {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private final String SQL_INSERT_USER = "INSERT into users (userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String SQL_DELETE_USER = "DELETE FROM users WHERE id=?";

    private final String SQL_TAKE_ALL_USERS =
            "SELECT id, userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id" +
                    " FROM users";

    private final String SQL_FIND_ALL_SUPERVISORS =
            "SELECT id, userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id" +
                    " FROM users" +
                    " WHERE userType='SUPERVISOR'";

    private final String SQL_FIND_USER_BY_USERNAME_AND_PASS = "SELECT id, userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id" +
            " FROM users WHERE username = ? AND password = ? ";

    private final String SQL_FIND_USER_BY_EMAIL_AND_PASS = "SELECT id, userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id" +
            " FROM users WHERE email = ? AND password = ? ";

    private final String SQL_FIND_USER_BY_EMAIL = "SELECT id, userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id" +
            " FROM users WHERE email = ?";

    private final String SQL_FIND_USER_BY_USERNAME = "SELECT id, userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id" +
            " FROM users WHERE username = ?";

    private final String SQL_FIND_USER_BY_ID = "SELECT id, userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id" +
            " FROM users WHERE id=?";

    private final String SQL_FIND_BY_USERNAME = "SELECT password FROM users WHERE username = ?";

    private final String SQL_FIND_BY_EMAIl = "SELECT password FROM users WHERE email = ?";

    private final String SQL_UPDATE = "UPDATE users" +
            " SET userType=?, first_name=?, surname=?, email=?, username=?, password=?, date_of_birth=?, weight=?, height=?, rating=?" +
            " WHERE id=?";

    private final String SQL_UPDATE_USER_TYPE = "UPDATE users SET userType=? WHERE id=?";

    private final String SQL_UPDATE_RATING = "UPDATE users SET rating=? WHERE id=?";

    private final String SQL_UPDATE_REQUEST_FOR_SUPERVISOR = "UPDATE users SET requested_supervisor_id=? WHERE id=?";

    private final String SQL_UPDATE_SUPERVISOR_ID = "UPDATE users SET supervisor_id=? WHERE id=?";

    private final String SQL_SELECT_SUPERVISOR_OF_USER = "SELECT supervisor_id FROM users WHERE id=?";

    private final String SQL_DELETE_REQUEST_FOR_SUPERVISOR = "UPDATE users SET requested_supervisor_id=NULL WHERE id=?";

    private final String SQL_FIND_USERS_OF_SUPERVISOR = "SELECT id, userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id" +
            " FROM users WHERE supervisor_id=?";

    private final String SQL_FIND_REQUESTS_FOR_SUPERVISOR = "SELECT id, userType, first_name, surname, email, username, password, date_of_birth, weight, height, rating, supervisor_id" +
            " FROM users WHERE requested_supervisor_id=?";

    private final String SQL_FIND_REQUESTED_SUPERVISOR_ID = "SELECT requested_supervisor_id FROM users WHERE id=?";

    private final String SQL_FIND_SUPERVISOR_ID = "SELECT supervisor_id FROM users WHERE id=?";


    @Override
    public void insert(User user) throws DaoException {
        insertOrUpdateUser(user, SQL_INSERT_USER);
    }

    @Override
    public void delete(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        insertOrUpdateUser(user, SQL_UPDATE);
    }

    private void insertOrUpdateUser(User user, String sqlQuery) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUserType().name());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getDateOfBirth());
            preparedStatement.setDouble(8, user.getWeight());
            preparedStatement.setDouble(9, user.getHeight());
            preparedStatement.setDouble(10, user.getRating());
            preparedStatement.setInt(11, user.getSupervisorId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public User findById(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User.Builder()
                        .setUserId(resultSet.getInt(1))
                        .setUserType(UserType.valueOf(resultSet.getString(2)))
                        .setFirstName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setEmail(resultSet.getString(5))
                        .setUsername(resultSet.getString(6))
                        .setPassword(resultSet.getString(7))
                        .setDateOfBirth(resultSet.getString(8))
                        .setWeight(resultSet.getDouble(9))
                        .setHeight(resultSet.getDouble(10))
                        .setRating(resultSet.getDouble(11))
                        .setSupervisorId(resultSet.getInt(12))
                        .build();
            }

            return null;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
    }

    @Override
    public List<User> findByUsernameAndPass(String username, String password) throws DaoException {
        return findByTwoParams(username, password, SQL_FIND_USER_BY_USERNAME_AND_PASS, SQL_FIND_BY_USERNAME);
    }

    @Override
    public List<User> findByEmailAndPass(String email, String password) throws DaoException {
        return findByTwoParams(email, password, SQL_FIND_USER_BY_EMAIL_AND_PASS, SQL_FIND_BY_EMAIl);
    }

    @Override
    public List<User> takeAllUsers() throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<User> userList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_ALL_USERS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User.Builder()
                        .setUserId(resultSet.getInt(1))
                        .setUserType(UserType.valueOf(resultSet.getString(2)))
                        .setFirstName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setEmail(resultSet.getString(5))
                        .setUsername(resultSet.getString(6))
                        .setPassword(resultSet.getString(7))
                        .setDateOfBirth(resultSet.getString(8))
                        .setWeight(resultSet.getDouble(9))
                        .setHeight(resultSet.getDouble(10))
                        .setRating(resultSet.getDouble(11))
                        .setSupervisorId(resultSet.getInt(12))
                        .build()
                );
            }
            return userList;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<User> findAllSupervisors() throws DaoException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_SUPERVISORS);
            resultSet = preparedStatement.executeQuery();

            List<User> supervisorList = new ArrayList<>();

            while (resultSet.next()) {
                supervisorList.add(new User.Builder()
                        .setUserId(resultSet.getInt(1))
                        .setUserType(UserType.valueOf(resultSet.getString(2)))
                        .setFirstName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setEmail(resultSet.getString(5))
                        .setUsername(resultSet.getString(6))
                        .setPassword(resultSet.getString(7))
                        .setDateOfBirth(resultSet.getString(8))
                        .setWeight(resultSet.getDouble(9))
                        .setHeight(resultSet.getDouble(10))
                        .setRating(resultSet.getDouble(11))
                        .setSupervisorId(resultSet.getInt(12))
                        .build()
                );
            }
            return supervisorList;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateUserType(int userId, String userType) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_TYPE);
            preparedStatement.setString(1, userType);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateRating(int supervisorId, double averageRating) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_RATING);
            preparedStatement.setDouble(1, averageRating);
            preparedStatement.setInt(2, supervisorId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    private List<User> findByTwoParams(String value1, String value2, String sqlQuery1, String sqlQuery2) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<User> users = new ArrayList<>();

        String pass = findPassByValue(value1, sqlQuery2);

        if (pass != null && BCrypt.checkpw(value2, pass)) {
            try {
                preparedStatement = connection.prepareStatement(sqlQuery1);
                preparedStatement.setString(1, value1);
                preparedStatement.setString(2, pass);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    users.add(new User.Builder()
                            .setUserId(resultSet.getInt(1))
                            .setUserType(UserType.valueOf(resultSet.getString(2)))
                            .setFirstName(resultSet.getString(3))
                            .setSurname(resultSet.getString(4))
                            .setEmail(resultSet.getString(5))
                            .setUsername(resultSet.getString(6))
                            .setPassword(resultSet.getString(7))
                            .setDateOfBirth(resultSet.getString(8))
                            .setWeight(resultSet.getDouble(9))
                            .setHeight(resultSet.getDouble(10))
                            .setRating(resultSet.getDouble(11))
                            .setSupervisorId(resultSet.getInt(12))
                            .build()
                    );
                }

                return users;

            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                ConnectionPool.getInstance().returnConnection(connection);
                closePreparedStatement(preparedStatement);
                closeResultSet(resultSet);
            }
        }

        return users;
    }

    private String findPassByValue(String value, String sqlQuery) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, value);

            resultSet = preparedStatement.executeQuery();

            String password = null;

            if (resultSet.next()) {
                password = resultSet.getString("password");
            }

            return password;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
    }

    @Override
    public boolean containsEmail(String email) throws DaoException {
        return dbContains(email, SQL_FIND_USER_BY_EMAIL);
    }

    @Override
    public boolean containsUsername(String username) throws DaoException {
        return dbContains(username, SQL_FIND_USER_BY_USERNAME);
    }

    @Override
    public void putRequestForSupervisor(int userId, int supervisorId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_REQUEST_FOR_SUPERVISOR);
            preparedStatement.setInt(1, supervisorId);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }


    @Override
    public void updateSupervisorId(int supervisorId, int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_SUPERVISOR_ID);
            preparedStatement.setInt(1, supervisorId);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public boolean containsSupervisor(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_SUPERVISOR_OF_USER);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next() && resultSet.getInt(1) != 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
    }

    @Override
    public boolean containsRequestForSupervisor(int userId) throws DaoException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_REQUESTED_SUPERVISOR_ID);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next() && resultSet.getInt(1) != 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

    }

    @Override
    public void deleteRequestForSupervisor(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_REQUEST_FOR_SUPERVISOR);
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public List<User> findUsersOfSupervisor(int supervisorId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<User> usersOfSupervisor = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_USERS_OF_SUPERVISOR);
            preparedStatement.setInt(1, supervisorId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usersOfSupervisor.add(new User.Builder()
                        .setUserId(resultSet.getInt(1))
                        .setUserType(UserType.valueOf(resultSet.getString(2)))
                        .setFirstName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setEmail(resultSet.getString(5))
                        .setUsername(resultSet.getString(6))
                        .setPassword(resultSet.getString(7))
                        .setDateOfBirth(resultSet.getString(8))
                        .setWeight(resultSet.getDouble(9))
                        .setHeight(resultSet.getDouble(10))
                        .setRating(resultSet.getDouble(11))
                        .setSupervisorId(resultSet.getInt(12))
                        .build()
                );
            }

            return usersOfSupervisor;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
    }

    @Override
    public List<User> findRequestsForSupervisor(int supervisorId) throws DaoException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<User> usersOfSupervisor = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_REQUESTS_FOR_SUPERVISOR);
            preparedStatement.setInt(1, supervisorId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usersOfSupervisor.add(new User.Builder()
                        .setUserId(resultSet.getInt(1))
                        .setUserType(UserType.valueOf(resultSet.getString(2)))
                        .setFirstName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setEmail(resultSet.getString(5))
                        .setUsername(resultSet.getString(6))
                        .setPassword(resultSet.getString(7))
                        .setDateOfBirth(resultSet.getString(8))
                        .setWeight(resultSet.getDouble(9))
                        .setHeight(resultSet.getDouble(10))
                        .setRating(resultSet.getDouble(11))
                        .setSupervisorId(resultSet.getInt(12))
                        .build()
                );
            }

            return usersOfSupervisor;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }


    }

    @Override
    public int findRequestedSupervisorId(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_REQUESTED_SUPERVISOR_ID);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return resultSet.getInt(1);
            }else{
                throw new DaoException("There is no such user id");
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
    }

    @Override
    public int findSupervisorId(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_SUPERVISOR_ID);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return resultSet.getInt(1);
            }else{
                throw new DaoException("There is no such user id");
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

    }


    private boolean dbContains(String value, String sqlQuery) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, value);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
    }
}
