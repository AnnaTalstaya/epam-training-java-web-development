package by.talstaya.crackertracker.dao.rating;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.dao.RatingDao;
import by.talstaya.crackertracker.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingDaoImpl implements RatingDao {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String SQL_FIND_USER_AND_SUPERVISOR = "SELECT id FROM ratings WHERE user_id=? AND supervisor_id=?";

    private static final String SQL_UPDATE_RATING = "UPDATE ratings SET rating=? WHERE user_id=? AND supervisor_id=?";

    private static final String SQL_INSERT = "INSERT into ratings (user_id, supervisor_id, rating) VALUES (?, ?, ?)";

    private static final String SQL_TAKE_AVERAGE_RATING = "SELECT AVG(rating) FROM ratings WHERE supervisor_id=?";

    private static final String SQL_TAKE_RATING_BY_USER = "SELECT rating FROM ratings WHERE user_id=? AND supervisor_id=?";

    private static final String SQL_DELETE_SUPERVISOR = "DELETE FROM ratings WHERE supervisor_id=?";

    @Override
    public boolean containsUserAndSupervisor(int userId, int supervisorId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_AND_SUPERVISOR);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, supervisorId);

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

    @Override
    public void updateRating(int userId, int supervisorId, int rating) throws DaoException {
        PreparedStatement preparedStatement = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_RATING);
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, supervisorId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void insert(int userId, int supervisorId, int rating) throws DaoException {
        PreparedStatement preparedStatement = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, supervisorId);
            preparedStatement.setInt(3, rating);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public double takeAverageRating(int supervisorId) throws DaoException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_AVERAGE_RATING);
            preparedStatement.setInt(1, supervisorId);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return resultSet.getDouble(1);
            }else{
                throw new DaoException("Impossible to count average rating");
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
    public double takeRatingByUser(int userId, int supervisorId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_TAKE_RATING_BY_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, supervisorId);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return resultSet.getInt(1);
            }else{
                return 0;
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
    public void deleteSupervisor(int supervisorId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_SUPERVISOR);
            preparedStatement.setInt(1, supervisorId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }


    public void closeResultSet(ResultSet resultSet) throws DaoException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            throw new DaoException("Error in the query to the database.");
        }
    }

    public void closePreparedStatement(PreparedStatement preparedStatement) throws DaoException {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            throw new DaoException("Statement is not created.");
        }
    }
}
