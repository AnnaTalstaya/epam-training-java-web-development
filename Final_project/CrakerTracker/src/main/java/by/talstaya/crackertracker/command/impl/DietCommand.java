package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DietCommand implements Command {

    private final static String USER = "User";
    private final static String MEAL_DATES = "mealDates";
    private final static String MEAL_DATE = "mealDate";
    private final static String SHOW_DIET = "show_diet";
    private final static String SELECTED_DATE = "selected_date";
    private final static String NO_MEAL = "noMeal";

    private final static String DIET_OF_USER_FOR_SUPERVISOR = "dietOfUserForSupervisor";
    private final static String USER_ID = "userId";
    private final static String USER_ID_FOR_SUPERVISOR = "userIdForSupervisor";

    private static final String PAGE_PATH_PARAM = "pagePath";

    private List<UserType> userTypeList;

    public DietCommand() {
        userTypeList = Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int userId;
        if ((request.getParameter(DIET_OF_USER_FOR_SUPERVISOR) != null
                && Boolean.parseBoolean(request.getParameter(DIET_OF_USER_FOR_SUPERVISOR)))) {
            userId = Integer.parseInt(request.getParameter(USER_ID));
            request.setAttribute(USER_ID_FOR_SUPERVISOR, userId);
            request.setAttribute(DIET_OF_USER_FOR_SUPERVISOR, true);
        } else if (request.getAttribute(DIET_OF_USER_FOR_SUPERVISOR) != null
                && (boolean) request.getAttribute(DIET_OF_USER_FOR_SUPERVISOR)) {
            userId = (int) request.getAttribute(USER_ID);
            request.setAttribute(USER_ID_FOR_SUPERVISOR, userId);
            request.setAttribute(DIET_OF_USER_FOR_SUPERVISOR, true);
        } else {
            User user = (User) request.getSession().getAttribute(USER);
            userId = user.getUserId();
        }

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

        if(request.getAttribute(PAGE_PATH_PARAM) != null){
            return (String)request.getAttribute(PAGE_PATH_PARAM);
        } else {
            return JspPath.DIET.getUrl();
        }

    }
}
