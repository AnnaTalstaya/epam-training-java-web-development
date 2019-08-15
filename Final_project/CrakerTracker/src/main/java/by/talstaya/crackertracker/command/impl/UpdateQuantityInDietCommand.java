package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateQuantityInDietCommand implements Command {

    private static final String QUANTITY = "quantity";
    private static final String MEAL_ID = "mealId";
    private static final String RESPONSE = "response";
    private static final String STRING_REGEX_NUMBER = "^\\d+$";

    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    private List<UserType> userTypeList;

    public UpdateQuantityInDietCommand() {
        userTypeList = Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Pattern pattern = Pattern.compile(STRING_REGEX_NUMBER);
        Matcher matcher = pattern.matcher(request.getParameter(QUANTITY));

        if(matcher.matches()){
            int quantity = Integer.parseInt(request.getParameter(QUANTITY));
            int mealId = Integer.parseInt(request.getParameter(MEAL_ID));

            MealService mealService = new MealServiceImpl();
            mealService.updateQuantity(mealId, quantity);

            request.setAttribute(RESPONSE, true);
            return request.getHeader("Referer");
        } else {
            request.setAttribute(ERROR, "Error data");
            request.setAttribute(STATUS_CODE, 404);
            return JspPath.ERROR.getUrl();
        }


    }
}
