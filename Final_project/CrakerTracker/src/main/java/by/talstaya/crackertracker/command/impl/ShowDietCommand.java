package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
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

/**
 * This class is used to show user diet of some date to user
 *
 * @author Anna Talstaya
 * @version 1.0
 */
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
    private static final String COMMENTS_FOR_USER_LIST = "commentForUserList";

    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if(request.getParameter(MEAL_DATE) != null) {
            String mealDate = request.getParameter(MEAL_DATE);
            if(mealDate == null) {
                mealDate = (String)request.getAttribute(MEAL_DATE);
            }

            User user = (User) request.getSession().getAttribute(USER);
            int userId = user.getUserId();

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
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return JspPath.ERROR.getUrl();
        }

    }

}
