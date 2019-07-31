package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.exception.DaoException;

public abstract class RatingDao {

    public abstract boolean containsUserAndSupervisor(int userId, int supervisorId) throws DaoException;

    public abstract void updateRating(int userId, int supervisorId, int rating) throws DaoException;

    public abstract void insert(int userId, int supervisorId, int rating) throws DaoException;

    public abstract double takeAverageRating(int supervisorId) throws DaoException;

    public abstract double takeRatingByUser(int userId) throws DaoException;
}
