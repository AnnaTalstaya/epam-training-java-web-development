package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.Meal;
import by.talstaya.crackertracker.entity.MealTime;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.MealTimeService;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;
import by.talstaya.crackertracker.service.impl.MealTimeServiceImpl;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddMealCommand implements Command {

    private ResourceBundle rb;

    private final String USER = "User";
    private final String PRODUCT = "product";
    private final String PRODUCT_ID = "productId";
    private final String MEAL_DATE = "mealDate";
    private final String MEAL_TIME = "mealTime";
    private final String QUANTITY = "quantity";
    private final String OK = "ok";
    private final String ERROR = "error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Locale locale = new Locale(String.valueOf(request.getSession().getAttribute("locale")));
        rb = ResourceBundle.getBundle("message", locale);

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
                request.setAttribute(OK, rb.getString("message.added"));

                request.setAttribute(PRODUCT, product);
                request.setAttribute(MEAL_DATE, mealDate);
                request.setAttribute(MEAL_TIME, stringMealTime);
                request.setAttribute(QUANTITY, quantity);
                request.setAttribute(OK, rb.getString("message.added"));

            } catch (ServiceException e) {
                request.setAttribute(ERROR, e.getMessage());
                return JspPath.ERROR.getUrl();
            }
        }else{
            request.setAttribute(ERROR, rb.getString("product.error.product_id_not_found"));
            return JspPath.ERROR.getUrl();
        }

        return JspPath.PRODUCT.getUrl();

    }
}
