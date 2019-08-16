package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.exception.ServiceException;

/**
 * This class is a layer for interacting with RatingDao
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface RatingService {

    void updateRating(int userId, int supervisorId, int rating) throws ServiceException;

    double takeAverageRating(int supervisorId) throws ServiceException;

    double takeRatingByUser(int userId, int supervisorId) throws ServiceException;

    void deleteSupervisor(int supervisorId) throws ServiceException;
}
