package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.entity.MealTime;
import by.talstaya.crackertracker.exception.ServiceException;

/**
 * This class is a layer for interacting with MealTimeDao
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface MealTimeService {

    int findIdByMealTime(String mealTime) throws ServiceException;

    MealTime findMealTimeById(int mealTimeId) throws ServiceException;
}
