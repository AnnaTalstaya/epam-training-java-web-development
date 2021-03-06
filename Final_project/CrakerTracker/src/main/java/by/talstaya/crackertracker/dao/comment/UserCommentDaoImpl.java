package by.talstaya.crackertracker.dao.comment;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.dao.UserCommentDao;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserComment;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of UserCommentDao
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class UserCommentDaoImpl implements UserCommentDao {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String SQL_FIND_COMMENTS = "SELECT id, date_of_comment, mealDate, user_id, commentator_id, comment" +
            " FROM user_comments" +
            " WHERE user_id=? AND mealDate=?";

    private static final String SQL_INSERT_COMMENT = "INSERT into user_comments (date_of_comment, mealDate, user_id, commentator_id, comment)" +
            " VALUES (NOW(),?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE user_comments" +
            " SET date_of_comment=?, mealDate=?, user_id=?, commentator_id=?, comment=?" +
            " WHERE id=?";

    private static final String SQL_FIND_COMMENT_BY_ID =
            "SELECT id, date_of_comment, mealDate, user_id, commentator_id, comment" +
            " FROM user_comments WHERE id=?";

    private static final String SQL_DELETE_COMMENT = "DELETE FROM user_comments WHERE id=?";

    private static final String SQL_DELETE_COMMENTS_FOR_USER = "DELETE FROM user_comments WHERE user_id=?";

    private static final String SQL_DELETE_COMMENTS_BY_COMMENTATOR = "DELETE FROM user_comments WHERE commentator_id=?";

    private static final String SQL_DELETE_COMMENTS_FOR_USER_BY_DATE = "DELETE FROM user_comments WHERE user_id=? AND mealDate=?";

    @Override
    public List<UserComment> findComments(int userId, String mealDate) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<UserComment> userCommentList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_COMMENTS);

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, mealDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userCommentList.add(new UserComment.Builder()
                        .setCommentId(resultSet.getInt(1))
                        .setDateOfComment(resultSet.getTimestamp(2).toLocalDateTime())
                        .setMealDate(resultSet.getDate(3).toLocalDate())
                        .setUserId(resultSet.getInt(4))
                        .setCommentator(createUser(resultSet.getInt(5)))
                        .setComment(resultSet.getString(6))
                        .build()
                );
            }
            return userCommentList;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void deleteCommentsForUser(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENTS_FOR_USER);
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void deleteCommentsByCommentator(int commentatorId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENTS_BY_COMMENTATOR);
            preparedStatement.setInt(1, commentatorId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENTS_FOR_USER_BY_DATE);
            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, Date.valueOf(selectedDate));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    private User createUser(int userId) throws DaoException {
        UserService userService = new UserServiceImpl();
        try {
            return userService.findUserById(userId);
        } catch (ServiceException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void insert(UserComment userComment) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_COMMENT);
            preparedStatement.setDate(1, Date.valueOf(userComment.getMealDate()));
            preparedStatement.setInt(2, userComment.getUserId());
            preparedStatement.setInt(3, userComment.getCommentator().getUserId());
            preparedStatement.setString(4, userComment.getComment());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void delete(int commentId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENT);
            preparedStatement.setInt(1, commentId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(UserComment userComment) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(userComment.getDateOfComment()));
            preparedStatement.setDate(2, Date.valueOf(userComment.getMealDate()));
            preparedStatement.setInt(3, userComment.getUserId());
            preparedStatement.setInt(4, userComment.getCommentator().getUserId());
            preparedStatement.setString(5, userComment.getComment());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public UserComment findById(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_COMMENT_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return new UserComment.Builder()
                        .setCommentId(resultSet.getInt(1))
                        .setDateOfComment(resultSet.getTimestamp(2).toLocalDateTime())
                        .setMealDate(resultSet.getDate(3).toLocalDate())
                        .setUserId(resultSet.getInt(4))
                        .setCommentator(findUserById(resultSet.getInt(5)))
                        .setComment(resultSet.getString(6))
                        .build();
            } else {
                throw new DaoException("No comment with such id");
            }

        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    private User findUserById(int userId) throws ServiceException {
        UserService userService = new UserServiceImpl();
        return userService.findUserById(userId);
    }

}
