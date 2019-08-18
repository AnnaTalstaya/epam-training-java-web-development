package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * This class is used to show dates of meals of some user
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class DietCommand implements Command {

    private final static String USER = "User";
    private final static String MEAL_DATES = "mealDates";
    private final static String MEAL_DATE = "mealDate";
    private final static String SHOW_DIET = "show_diet";
    private final static String SELECTED_DATE = "selected_date";
    private final static String NO_MEAL = "noMeal";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User) request.getSession().getAttribute(USER);
        int userId = user.getUserId();

        MealService mealService = new MealServiceImpl();
        Set<String> dates = mealService.findDatesByUserId(userId);

        if (!dates.isEmpty()) {
            request.setAttribute(MEAL_DATES, dates);

            if (request.getAttribute(SHOW_DIET) != null && (boolean) request.getAttribute(SHOW_DIET)) {
                request.setAttribute(SELECTED_DATE, request.getParameter(MEAL_DATE));
            }
        } else {
            request.setAttribute(NO_MEAL, "You have not added anything to your diet");
        }

        return JspPath.DIET.getUrl();
    }
}
