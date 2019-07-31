package by.talstaya.crackertracker.service.impl;

import by.talstaya.crackertracker.dao.MealDao;
import by.talstaya.crackertracker.dao.meal.MealDaoImpl;
import by.talstaya.crackertracker.entity.Meal;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;

import java.util.List;
import java.util.Set;

public class MealServiceImpl implements MealService {

    private MealDao mealDao;

    public MealServiceImpl() {
        mealDao = new MealDaoImpl();
    }

    @Override
    public void insertMeal(Meal meal) throws ServiceException {
        try {
            mealDao.insert(meal);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteMeal(int mealId) throws ServiceException {
        try {
            mealDao.delete(mealId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateQuantity(int mealId, int quantity) throws ServiceException {
        try {
            mealDao.updateQuantity(mealId, quantity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<String> findDatesByUserId(int userId) throws ServiceException {
        try {
            return mealDao.findDatesByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Meal> findMealsByUserIdAndMealDateAndMealTime(int userId, String mealDate, String mealTime) throws ServiceException {
        try {
            return mealDao.findMealsByUserIdAndMealDateAndMealTime(userId, mealDate, mealTime);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int totalCaloriesByUserIdAndMealDate(int userId, String mealDate) throws ServiceException {
        try {
            return mealDao.totalCaloriesByUserIdAndMealDate(userId, mealDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int totalProteinsByUserIdAndMealDate(int userId, String mealDate) throws ServiceException {
        try {
            return mealDao.totalProteinsByUserIdAndMealDate(userId, mealDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int totalLipidsByUserIdAndMealDate(int userId, String mealDate) throws ServiceException {
        try {
            return mealDao.totalLipidsByUserIdAndMealDate(userId, mealDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int totalCarbohydratesByUserIdAndMealDate(int userId, String mealDate) throws ServiceException {
        try {
            return mealDao.totalCarbohydratesByUserIdAndMealDate(userId, mealDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
