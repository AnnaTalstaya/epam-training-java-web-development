package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.MealTime;
import by.talstaya.crackertracker.exception.DaoException;

public abstract class MealTimeDao extends BasicDao<MealTime> {

    public abstract int findIdByMealTime(String mealTime) throws DaoException;

    public abstract MealTime findMealTimeById(int mealTimeId) throws DaoException;
}
