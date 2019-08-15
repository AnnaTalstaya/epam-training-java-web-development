package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class DeleteMealCommand implements Command {

    private final static String USER = "User";

    private static final String MEAL_ID = "mealId";
    private static final String TOTAL_PRODUCTS = "totalProducts";
    private static final String MEAL_DATE = "mealDate";

    private static final String RESPONSE = "response";
    private static final String DIET_PATH = "/diet";


    private List<UserType> userTypeList;

    public DeleteMealCommand() {
        userTypeList = Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

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
            return request.getContextPath() + DIET_PATH;
        }
    }
}
