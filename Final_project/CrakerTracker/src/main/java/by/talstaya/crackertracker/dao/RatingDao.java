package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.exception.DaoException;

public interface RatingDao {

    boolean containsUserAndSupervisor(int userId, int supervisorId) throws DaoException;

    void updateRating(int userId, int supervisorId, int rating) throws DaoException;

    void insert(int userId, int supervisorId, int rating) throws DaoException;

    double takeAverageRating(int supervisorId) throws DaoException;

    double takeRatingByUser(int userId) throws DaoException;
}
