package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.Meal;
import by.talstaya.crackertracker.exception.DaoException;

import java.util.List;
import java.util.Set;

public abstract class MealDao extends BasicDao<Meal> {

    public abstract void updateQuantity(int mealId, int quantity) throws DaoException;

    public abstract Set<String> findDatesByUserId(int userId) throws DaoException;

    public abstract List<Meal> findMealsByUserIdAndMealDateAndMealTime(int userId, String mealDate, String mealTime) throws DaoException;

    public abstract Meal findMealByUserIdMealDateMealTimeProductId(Meal meal) throws DaoException;

    public abstract int totalCaloriesByUserIdAndMealDate(int userId, String mealDate) throws DaoException;

    public abstract int totalProteinsByUserIdAndMealDate(int userId, String mealDate) throws DaoException;

    public abstract int totalLipidsByUserIdAndMealDate(int userId, String mealDate) throws DaoException;

    public abstract int totalCarbohydratesByUserIdAndMealDate(int userId, String mealDate) throws DaoException;


}
