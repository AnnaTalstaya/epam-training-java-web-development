package by.talstaya.crackertracker.command.impl.supervisor;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDietForSupervisorCommand implements Command {

    private static final String USER = "User";
    private final static String MEAL_DATES = "mealDates";
    private final static String MEAL_DATE = "mealDate";
    private final static String SHOW_DIET = "show_diet";
    private final static String SELECTED_DATE = "selected_date";
    private final static String NO_MEAL = "noMeal";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String REGEX_NUMBER = "^[1-9]\\d*$";
    private final static String USER_ID_FOR_SUPERVISOR = "userIdForSupervisor";

    private List<UserType> userTypeList;

    public UserDietForSupervisorCommand() {
        userTypeList = Collections.singletonList(UserType.SUPERVISOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String stringUserId = null;

        if (request.getParameter(USER_ID_FOR_SUPERVISOR) != null) {
            stringUserId = request.getParameter(USER_ID_FOR_SUPERVISOR);
        }

        if (stringUserId != null) {

            Pattern pattern = Pattern.compile(REGEX_NUMBER);
            Matcher matcher = pattern.matcher(stringUserId);

            if (matcher.matches()) {
                int userId = Integer.parseInt(stringUserId);

                UserService userService = new UserServiceImpl();
                User supervisorOfUser = userService.findSupervisorOfUser(userId);
                User supervisor = (User) request.getSession().getAttribute(USER);

                if (supervisorOfUser != null && supervisor.getUserId() == supervisorOfUser.getUserId()) {
                    MealService mealService = new MealServiceImpl();
                    Set<String> dates = mealService.findDatesByUserId(userId);

                    if (!dates.isEmpty()) {
                        request.setAttribute(MEAL_DATES, dates);

                        if (request.getAttribute(SHOW_DIET) != null && (boolean) request.getAttribute(SHOW_DIET)) {
                            request.setAttribute(SELECTED_DATE, request.getParameter(MEAL_DATE));
                        }
                    } else {
                        request.setAttribute(NO_MEAL, "User have not added anything to his diet");
                    }
                    request.setAttribute(USER_ID_FOR_SUPERVISOR, userId);

                    return JspPath.USER_DIET_FOR_SUPERVISOR.getUrl();
                } else {
                    request.setAttribute(ERROR, "You don't have a user with such id");
                    request.setAttribute(STATUS_CODE, 404);
                    return JspPath.ERROR.getUrl();
                }
            } else {
                request.setAttribute(ERROR, "Error data");
                request.setAttribute(STATUS_CODE, 404);
                return JspPath.ERROR.getUrl();
            }
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return JspPath.ERROR.getUrl();
        }
    }
}
