package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.MealTime;
import by.talstaya.crackertracker.exception.DaoException;

public interface MealTimeDao extends BasicDao<MealTime> {

    int findIdByMealTime(String mealTime) throws DaoException;

    MealTime findMealTimeById(int mealTimeId) throws DaoException;
}
