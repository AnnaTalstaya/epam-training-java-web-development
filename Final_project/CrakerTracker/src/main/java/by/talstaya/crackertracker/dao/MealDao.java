package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.Meal;
import by.talstaya.crackertracker.exception.DaoException;

import java.util.List;
import java.util.Set;

public interface MealDao extends BasicDao<Meal> {

    void updateQuantity(int mealId, int quantity) throws DaoException;

    Set<String> findDatesByUserId(int userId) throws DaoException;

    List<Meal> findMealsByUserIdAndMealDateAndMealTime(int userId, String mealDate, String mealTime) throws DaoException;

    Meal findMealByUserIdMealDateMealTimeProductId(Meal meal) throws DaoException;

    int totalCaloriesByUserIdAndMealDate(int userId, String mealDate) throws DaoException;

    int totalProteinsByUserIdAndMealDate(int userId, String mealDate) throws DaoException;

    int totalLipidsByUserIdAndMealDate(int userId, String mealDate) throws DaoException;

    int totalCarbohydratesByUserIdAndMealDate(int userId, String mealDate) throws DaoException;

    void deleteMealByUserId(int userId) throws DaoException;
}
