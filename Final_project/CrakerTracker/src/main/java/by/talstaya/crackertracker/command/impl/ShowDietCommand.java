package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.CommentForUser;
import by.talstaya.crackertracker.entity.Meal;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.CommentForUserService;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.impl.CommentForUserServiceImpl;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class ShowDietCommand implements Command {

    private static final String USER = "User";
    private static final String MEAL_DATE = "mealDate";
    private static final String BREAKFAST = "breakfast";
    private static final String LUNCH = "lunch";
    private static final String DINNER = "dinner";
    private static final String BREAKFAST_MEALS = "breakfastMeals";
    private static final String LUNCH_MEALS = "lunchMeals";
    private static final String DINNER_MEALS = "dinnerMeals";
    private static final String SHOW_DIET = "show_diet";
    private static final String TOTAL_CALORIES = "totalCalories";
    private static final String TOTAL_PROTEINS = "totalProteins";
    private static final String TOTAL_LIPIDS = "totalLipids";
    private static final String TOTAL_CARBOHYDRATES = "totalCarbohydrates";
    private static final String TOTAL_PRODUCTS = "totalProducts";

    private static final String USER_ID_FOR_SUPERVISOR = "userIdForSupervisor";
    private static final String DIET_OF_USER_FOR_SUPERVISOR = "dietOfUserForSupervisor";
    private static final String USER_ID = "userId";
    private static final String COMMENTS_FOR_USER_LIST = "commentForUserList";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String mealDate = request.getParameter(MEAL_DATE);
        if(mealDate == null) {
            mealDate = (String)request.getAttribute(MEAL_DATE);
        }

        int userId;
        if(request.getParameter(USER_ID_FOR_SUPERVISOR) != null && !request.getParameter(USER_ID_FOR_SUPERVISOR).isEmpty()){
            userId = Integer.parseInt(request.getParameter(USER_ID_FOR_SUPERVISOR));
            request.setAttribute(DIET_OF_USER_FOR_SUPERVISOR, true);
            request.setAttribute(USER_ID, userId);

        } else {
            User user = (User) request.getSession().getAttribute(USER);
            userId = user.getUserId();
        }

        MealService mealService = new MealServiceImpl();

        List<Meal> breakfastMeals = mealService.findMealsByUserIdAndMealDateAndMealTime(userId, mealDate, BREAKFAST);
        List<Meal> lunchMeals = mealService.findMealsByUserIdAndMealDateAndMealTime(userId, mealDate, LUNCH);
        List<Meal> dinnerMeals = mealService.findMealsByUserIdAndMealDateAndMealTime(userId, mealDate, DINNER);

        if (!breakfastMeals.isEmpty()) {
            request.setAttribute(BREAKFAST_MEALS, breakfastMeals);
        }
        if (!lunchMeals.isEmpty()) {
            request.setAttribute(LUNCH_MEALS, lunchMeals);
        }
        if (!dinnerMeals.isEmpty()) {
            request.setAttribute(DINNER_MEALS, dinnerMeals);
        }

        int totalCalories = mealService.totalCaloriesByUserIdAndMealDate(userId, mealDate);
        int totalProteins = mealService.totalProteinsByUserIdAndMealDate(userId, mealDate);
        int totalLipids = mealService.totalLipidsByUserIdAndMealDate(userId, mealDate);
        int totalCarbohydrates = mealService.totalCarbohydratesByUserIdAndMealDate(userId, mealDate);

        CommentForUserService commentForUserService = new CommentForUserServiceImpl();
        List<CommentForUser> commentForUserList = commentForUserService.findComments(userId, mealDate);
        Collections.reverse(commentForUserList);

        request.setAttribute(COMMENTS_FOR_USER_LIST, commentForUserList);

        request.setAttribute(SHOW_DIET, true);
        request.setAttribute(MEAL_DATE, mealDate);

        request.setAttribute(TOTAL_CALORIES, totalCalories);
        request.setAttribute(TOTAL_PROTEINS, totalProteins);
        request.setAttribute(TOTAL_LIPIDS, totalLipids);
        request.setAttribute(TOTAL_CARBOHYDRATES, totalCarbohydrates);

        request.setAttribute(TOTAL_PRODUCTS, breakfastMeals.size() + lunchMeals.size() + dinnerMeals.size());

        return new DietCommand().execute(request, response);
    }

}
