package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.Entity;
import by.talstaya.crackertracker.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BasicDao<T extends Entity> {

    private static final Logger LOGGER = LogManager.getLogger("name");

    public abstract void insert(T entity) throws DaoException;

    public abstract void delete(int id) throws DaoException;

    public abstract void update(T entity) throws DaoException;

    public abstract T findById(int id) throws DaoException;

    public void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void closePreparedStatement(Statement statement) {
        if (statement != null) {
            try {
               statement.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

}
