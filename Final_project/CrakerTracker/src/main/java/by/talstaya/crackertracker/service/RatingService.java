package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.exception.ServiceException;

public interface RatingService {

    void updateRating(int userId, int supervisorId, int rating) throws ServiceException;

    double takeAverageRating(int supervisorId) throws ServiceException;

    double takeRatingByUser(int userId, int supervisorId) throws ServiceException;
}
