package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.entity.Meal;
import by.talstaya.crackertracker.exception.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * This class is a layer for interacting with MealDao
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface MealService {

    void insertMeal(Meal meal) throws ServiceException;

    void deleteMeal(int mealId) throws ServiceException;

    void updateQuantity(int mealId, int quantity) throws ServiceException;

    Set<String> findDatesByUserId(int userId) throws ServiceException;

    List<Meal> findMealsByUserIdAndMealDateAndMealTime(int userId, String mealDate, String mealTime) throws ServiceException;

    int totalCaloriesByUserIdAndMealDate(int userId, String mealDate) throws ServiceException;

    int totalProteinsByUserIdAndMealDate(int userId, String mealDate) throws ServiceException;

    int totalLipidsByUserIdAndMealDate(int userId, String mealDate) throws ServiceException;

    int totalCarbohydratesByUserIdAndMealDate(int userId, String mealDate) throws ServiceException;

    void deleteMealByUserId(int userId) throws ServiceException;
}
