package by.talstaya.crackertracker.service.impl;

import by.talstaya.crackertracker.dao.RatingDao;
import by.talstaya.crackertracker.dao.rating.RatingDaoImpl;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.RatingService;

public class RatingServiceImpl implements RatingService {

    private RatingDao ratingDao;

    public RatingServiceImpl() {
        this.ratingDao = new RatingDaoImpl();
    }

    @Override
    public void updateRating(int userId, int supervisorId, int rating) throws ServiceException {
        try {
            if(ratingDao.containsUserAndSupervisor(userId, supervisorId)){
                ratingDao.updateRating(userId, supervisorId, rating);
            } else {
                ratingDao.insert(userId, supervisorId, rating);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public double takeAverageRating(int supervisorId) throws ServiceException {
        try {
            return ratingDao.takeAverageRating(supervisorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public double takeRatingByUser(int userId, int supervisorId) throws ServiceException {
        try {
            return ratingDao.takeRatingByUser(userId, supervisorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteSupervisor(int supervisorId) throws ServiceException {
        try {
            ratingDao.deleteSupervisor(supervisorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
