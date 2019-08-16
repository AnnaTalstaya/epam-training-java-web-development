package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.command.Pagination;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used for to visit page with all products with default params
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class VisitProductListCommand implements Command, Pagination {

    private List<UserType> userTypeList;

    private static final String PRODUCTS = "products";

    private static final int NUMBER_PRODUCTS_PER_PAGE = 8;

    private static final String PRODUCTS_PER_PAGE = "productsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String START_INDEX_OF_PRODUCT_LIST = "startIndexOfProductList";
    private static final String CHANGE_PAGE = "changePage";

    private static final String MIN_CALORIES = "minCalories";
    private static final String MIN_PROTEINS = "minProteins";
    private static final String MIN_LIPIDS = "minLipids";
    private static final String MIN_CARBOHYDRATES = "minCarbohydrates";
    private static final String MAX_CALORIES = "maxCalories";
    private static final String MAX_PROTEINS = "maxProteins";
    private static final String MAX_LIPIDS = "maxLipids";
    private static final String MAX_CARBOHYDRATES = "maxCarbohydrates";

    private static final String ERROR = "error";

    public VisitProductListCommand() {
        userTypeList = Arrays.asList(UserType.ANONYMOUS, UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if (request.getSession().getAttribute(ERROR) != null) {
            request.setAttribute(ERROR, request.getSession().getAttribute(ERROR));
            request.getSession().setAttribute(ERROR, null);
        }

        initPaginationParams(request,
                NUMBER_PRODUCTS_PER_PAGE,
                PRODUCTS_PER_PAGE,
                INDEX_OF_PAGE,
                START_INDEX_OF_PRODUCT_LIST,
                CHANGE_PAGE);

        ProductService productService = new ProductServiceImpl();
        List<Product> products = productService.takeAllProducts();

        if (!products.isEmpty()) {
            request.setAttribute(MIN_CALORIES, productService.findMinCalories());
            request.setAttribute(MIN_PROTEINS, productService.findMinProteins());
            request.setAttribute(MIN_LIPIDS, productService.findMinLipids());
            request.setAttribute(MIN_CARBOHYDRATES, productService.findMinCarbohydrates());

            request.setAttribute(MAX_CALORIES, productService.findMaxCalories());
            request.setAttribute(MAX_PROTEINS, productService.findMaxProteins());
            request.setAttribute(MAX_LIPIDS, productService.findMaxLipids());
            request.setAttribute(MAX_CARBOHYDRATES, productService.findMaxCarbohydrates());
        }

        request.setAttribute(PRODUCTS, products);

        return JspPath.PRODUCT_LIST.getUrl();
    }
}
