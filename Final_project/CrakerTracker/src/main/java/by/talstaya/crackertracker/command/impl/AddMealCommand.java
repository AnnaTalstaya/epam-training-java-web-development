package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.*;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.MealTimeService;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;
import by.talstaya.crackertracker.service.impl.MealTimeServiceImpl;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddMealCommand implements Command {

    private static final String USER = "User";
    private static final String PRODUCT_ID = "productId";
    private static final String MEAL_DATE = "mealDate";
    private static final String MEAL_TIME = "mealTime";
    private static final String QUANTITY = "quantity";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String STRING_REGEX_NUMBER = "^[1-9]\\d{0,2}$";
    private static final String RESPONSE = "response";

    private List<UserType> userTypeList;

    public AddMealCommand() {
        userTypeList = Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute(USER);
        int productId = Integer.parseInt(request.getParameter(PRODUCT_ID));
        String mealDate = request.getParameter(MEAL_DATE);
        String stringMealTime = request.getParameter(MEAL_TIME);

        Pattern pattern = Pattern.compile(STRING_REGEX_NUMBER);
        Matcher matcher = pattern.matcher(request.getParameter(QUANTITY));

        if(matcher.matches()){
            int quantity = Integer.parseInt(request.getParameter(QUANTITY));

            ProductService productService = new ProductServiceImpl();
            try {
                Product product = productService.findByProductId(productId);

                MealService mealService = new MealServiceImpl();
                MealTimeService mealTimeService = new MealTimeServiceImpl();

                int idMealTime = mealTimeService.findIdByMealTime(stringMealTime);

                Meal meal = new Meal.Builder()
                        .setUser(user)
                        .setProduct(product)
                        .setDate(mealDate)
                        .setMealTime(new MealTime.Builder()
                                .setMealTimeId(idMealTime)
                                .setMealTimeValue(stringMealTime)
                                .build())
                        .setQuantity(quantity)
                        .build();
                mealService.insertMeal(meal);

            } catch (ServiceException e) {
                request.setAttribute(ERROR, e.getMessage());
                request.setAttribute(STATUS_CODE, 500);
                return JspPath.ERROR.getUrl();
            }
        }else{
            request.setAttribute(ERROR, "Error quantity");
            request.setAttribute(STATUS_CODE, 400);
            return JspPath.ERROR.getUrl();
        }

        request.setAttribute(RESPONSE, true);
        return request.getHeader("Referer");

    }
}
