package by.talstaya.crackertracker.dao.mealtime;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.dao.MealTimeDao;
import by.talstaya.crackertracker.entity.MealTime;
import by.talstaya.crackertracker.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is an implementation of MealTimeDao
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class MealTimeDaoImpl implements MealTimeDao {

    private static final String SQL_FIND_BY_MEAL_TIME = "SELECT id, meal_time FROM meal_time WHERE meal_time = ?";
    private static final String SQL_FIND_BY_ID = "SELECT id, meal_time FROM meal_time WHERE id=?";


    @Override
    public int findIdByMealTime(String mealTime) throws DaoException {
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_MEAL_TIME);
            preparedStatement.setString(1, mealTime);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);

            }
            return -1;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public MealTime findMealTimeById(int mealTimeId) throws DaoException {
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, mealTimeId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new MealTime.Builder()
                        .setMealTimeId(mealTimeId)
                        .setMealTimeValue(resultSet.getString(2))
                        .build();

            }
            return null;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void insert(by.talstaya.crackertracker.entity.MealTime entity) throws DaoException {
        //todo
    }

    @Override
    public void delete(int id) throws DaoException {
        //todo

    }

    @Override
    public void update(by.talstaya.crackertracker.entity.MealTime entity) throws DaoException {
        //todo

    }

    @Override
    public by.talstaya.crackertracker.entity.MealTime findById(int id) throws DaoException {
        return null;        //todo

    }
}
