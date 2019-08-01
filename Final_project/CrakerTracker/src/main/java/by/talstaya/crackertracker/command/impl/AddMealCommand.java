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

public class AddMealCommand implements Command {

    private static final String USER = "User";
    private static final String PRODUCT = "product";
    private static final String PRODUCT_ID = "productId";
    private static final String MEAL_DATE = "mealDate";
    private static final String MEAL_TIME = "mealTime";
    private static final String QUANTITY = "quantity";
    private static final String OK = "ok";
    private static final String ERROR = "error";

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
        int quantity = Integer.parseInt(request.getParameter(QUANTITY));

        if(productId != 0){
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

                request.setAttribute(PRODUCT, product);
                request.setAttribute(MEAL_DATE, mealDate);
                request.setAttribute(MEAL_TIME, stringMealTime);
                request.setAttribute(QUANTITY, quantity);
                request.setAttribute(OK, "Added successfully");

            } catch (ServiceException e) {
                request.setAttribute(ERROR, e.getMessage());
                return JspPath.ERROR.getUrl();
            }
        }else{
            request.setAttribute(ERROR, "Product with such id not found");
            return JspPath.ERROR.getUrl();
        }

        return JspPath.PRODUCT.getUrl();

    }
}
