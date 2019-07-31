package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.entity.MealTime;
import by.talstaya.crackertracker.exception.ServiceException;

public interface MealTimeService {

    int findIdByMealTime(String mealTime) throws ServiceException;

    MealTime findMealTimeById(int mealTimeId) throws ServiceException;
}
