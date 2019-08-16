package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.MealTime;
import by.talstaya.crackertracker.exception.DaoException;

/**
 * This class is a layer for interacting with mealTime database
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface MealTimeDao extends BasicDao<MealTime> {

    int findIdByMealTime(String mealTime) throws DaoException;

    MealTime findMealTimeById(int mealTimeId) throws DaoException;
}
