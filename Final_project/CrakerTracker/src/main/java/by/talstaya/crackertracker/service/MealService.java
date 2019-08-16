package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.entity.Meal;
import by.talstaya.crackertracker.exception.ServiceException;

import java.util.List;
import java.util.Set;

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
