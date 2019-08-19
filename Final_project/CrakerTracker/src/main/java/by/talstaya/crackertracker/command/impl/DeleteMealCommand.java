package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.UserCommentService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;
import by.talstaya.crackertracker.service.impl.UserCommentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * This class is used to delete meal from user diet
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class DeleteMealCommand implements Command {

    private final static String USER = "User";

    private static final String MEAL_ID = "mealId";
    private static final String TOTAL_PRODUCTS = "totalProducts";
    private static final String SELECTED_DATE = "selected_date";

    private static final String RESPONSE = "response";
    private static final String DIET_PATH = "/diet";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int mealId = Integer.parseInt(request.getParameter(MEAL_ID));
        int totalProducts = Integer.parseInt(request.getParameter(TOTAL_PRODUCTS));

        MealService mealService = new MealServiceImpl();
        mealService.deleteMeal(mealId);

        request.setAttribute(RESPONSE, true);
        if(totalProducts - 1 > 0){
            return request.getHeader("Referer");
        }else{
            User user = (User)request.getSession().getAttribute(USER);
            LocalDate selectedDate = LocalDate.parse(request.getParameter(SELECTED_DATE));

            UserCommentService userCommentService = new UserCommentServiceImpl();
            userCommentService.deleteCommentsForUserByDate(user.getUserId(), selectedDate);
            return request.getContextPath() + DIET_PATH;
        }
    }
}
