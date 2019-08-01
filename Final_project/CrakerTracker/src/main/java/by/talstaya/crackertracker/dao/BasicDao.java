package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.Entity;
import by.talstaya.crackertracker.exception.DaoException;
import org.apache.logging.log4j.LogManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface BasicDao<T extends Entity> {

    void insert(T entity) throws DaoException;

    void delete(int id) throws DaoException;

    void update(T entity) throws DaoException;

    T findById(int id) throws DaoException;

    default void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LogManager.getLogger("name").error(e.getMessage(), e);
            }
        }
    }

    default void closePreparedStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LogManager.getLogger("name").error(e.getMessage(), e);
            }
        }
    }

}
