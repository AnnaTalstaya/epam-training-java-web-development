package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMealCommand implements Command {

    private final String MEAL_ID = "mealId";
    private final String TOTAL_PRODUCTS = "totalProducts";
    private final String MEAL_DATE = "mealDate";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int mealId = Integer.parseInt(request.getParameter(MEAL_ID));
        int totalProducts = Integer.parseInt(request.getParameter(TOTAL_PRODUCTS));
        String mealDate = request.getParameter(MEAL_DATE);

        MealService mealService = new MealServiceImpl();
        mealService.deleteMeal(mealId);

        if(totalProducts - 1 > 0){
            request.setAttribute(MEAL_DATE, mealDate);
            return new ShowDietCommand().execute(request, response);
        }

        return new DietCommand().execute(request, response);
    }
}
