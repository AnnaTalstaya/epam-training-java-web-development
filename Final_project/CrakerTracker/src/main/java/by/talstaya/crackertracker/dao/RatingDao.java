package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.exception.DaoException;

/**
 * This class is a layer for interacting with rating database
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface RatingDao {

    boolean containsUserAndSupervisor(int userId, int supervisorId) throws DaoException;

    void updateRating(int userId, int supervisorId, int rating) throws DaoException;

    void insert(int userId, int supervisorId, int rating) throws DaoException;

    double takeAverageRating(int supervisorId) throws DaoException;

    double takeRatingByUser(int userId, int supervisorId) throws DaoException;

    void deleteSupervisor(int supervisorId) throws DaoException;
}
