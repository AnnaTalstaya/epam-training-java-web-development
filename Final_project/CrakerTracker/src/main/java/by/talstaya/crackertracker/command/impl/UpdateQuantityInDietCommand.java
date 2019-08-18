package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used for updating quantity of product in user diet
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class UpdateQuantityInDietCommand implements Command {

    private static final String QUANTITY = "quantity";
    private static final String MEAL_ID = "mealId";
    private static final String RESPONSE = "response";
    private static final String REGEX_QUANTITY = "^[1-9]\\d{0,2}$";

    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Pattern pattern = Pattern.compile(REGEX_QUANTITY);
        Matcher matcher = pattern.matcher(request.getParameter(QUANTITY));

        if(matcher.matches()){
            int quantity = Integer.parseInt(request.getParameter(QUANTITY));
            int mealId = Integer.parseInt(request.getParameter(MEAL_ID));

            MealService mealService = new MealServiceImpl();
            mealService.updateQuantity(mealId, quantity);

            request.setAttribute(RESPONSE, true);
            return request.getHeader("Referer");
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return JspPath.ERROR.getUrl();
        }


    }
}
