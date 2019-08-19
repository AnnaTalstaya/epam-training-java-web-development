package by.talstaya.crackertracker.service.impl;

import by.talstaya.crackertracker.dao.MealTimeDao;
import by.talstaya.crackertracker.dao.mealtime.MealTimeDaoImpl;
import by.talstaya.crackertracker.entity.MealTime;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealTimeService;

/**
 * This class is an implementation of MealTimeService
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class MealTimeServiceImpl implements MealTimeService {

    private MealTimeDao mealTimeDao;

    public MealTimeServiceImpl() {
        this.mealTimeDao = new MealTimeDaoImpl();
    }

    @Override
    public int findIdByMealTime(String mealTime) throws ServiceException {
        try {
            return mealTimeDao.findIdByMealTime(mealTime);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public MealTime findMealTimeById(int mealTimeId) throws ServiceException {
        try {
            return mealTimeDao.findById(mealTimeId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
