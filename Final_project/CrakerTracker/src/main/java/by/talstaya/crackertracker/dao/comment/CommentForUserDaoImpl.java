package by.talstaya.crackertracker.dao.comment;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.dao.CommentForUserDao;
import by.talstaya.crackertracker.entity.CommentForUser;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentForUserDaoImpl implements CommentForUserDao {

    private static final String SQL_FIND_COMMENTS = "SELECT id, date_of_comment, meal_date, user_id, commentator_id, comment" +
            " FROM comments_for_users" +
            " WHERE user_id=? AND meal_date=?";

    private static final String SQL_INSERT_COMMENT = "INSERT into comments_for_users (date_of_comment, meal_date, user_id, commentator_id, comment)" +
            " VALUES (NOW(),?,?,?,?)";

    private static final String SQL_DELETE_COMMENT = "DELETE FROM comments_for_users WHERE id=?";

    private static final String SQL_DELETE_COMMENTS_FOR_USER = "DELETE FROM comments_for_users WHERE user_id=?";

    private static final String SQL_DELETE_COMMENTS_BY_COMMENTATOR = "DELETE FROM comments_for_users WHERE commentator_id=?";

    @Override
    public List<CommentForUser> findComments(int userId, String mealDate) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<CommentForUser> commentForUserList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_COMMENTS);

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, mealDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                commentForUserList.add(new CommentForUser.Builder()
                        .setCommentId(resultSet.getInt(1))
                        .setDateOfComment(resultSet.getString(2))
                        .setMealDate(resultSet.getString(3))
                        .setUserId(resultSet.getInt(4))
                        .setCommentator(createUser(resultSet.getInt(5)))
                        .setComment(resultSet.getString(6))
                        .build()
                );
            }
            return commentForUserList;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
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
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
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
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
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
    public void insert(CommentForUser commentForUser) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_COMMENT);
            preparedStatement.setString(1, commentForUser.getMealDate());
            preparedStatement.setInt(2, commentForUser.getUserId());
            preparedStatement.setInt(3, commentForUser.getCommentator().getUserId());
            preparedStatement.setString(4, commentForUser.getComment());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
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
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(CommentForUser entity) throws DaoException {
        //todo
    }

    @Override
    public CommentForUser findById(int id) throws DaoException {
        return null; //todo
    }


}
