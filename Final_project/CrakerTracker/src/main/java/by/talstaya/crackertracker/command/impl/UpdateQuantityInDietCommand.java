package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateQuantityInDietCommand implements Command {

    private final String QUANTITY = "quantity";
    private final String MEAL_ID = "mealId";
    private final String MEAL_DATE = "mealDate";
    private final String OK = "ok";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int quantity = Integer.parseInt(request.getParameter(QUANTITY));
        int mealId = Integer.parseInt(request.getParameter(MEAL_ID));
        String mealDate = request.getParameter(MEAL_DATE);

        MealService mealService = new MealServiceImpl();
        mealService.updateQuantity(mealId, quantity);

        request.setAttribute(MEAL_DATE, mealDate);
        request.setAttribute(OK, true);
        return new ShowDietCommand().execute(request, response);
    }
}
