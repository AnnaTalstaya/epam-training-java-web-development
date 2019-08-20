package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used for to visit page with all products with default params
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class VisitProductListCommand implements Command {

    private static final String PRODUCTS = "products";

    private static final int NUMBER_PRODUCTS_PER_PAGE = 8;

    private static final String PRODUCTS_PER_PAGE = "productsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String PRODUCT_LIST_SIZE = "productListSize";
    private static final String VISIT_PRODUCT_LIST_COMMAND = "visit_product_list";
    private static final String COMMAND_VALUE = "commandValue";

    private static final String MIN_CALORIES = "minCalories";
    private static final String MIN_PROTEINS = "minProteins";
    private static final String MIN_LIPIDS = "minLipids";
    private static final String MIN_CARBOHYDRATES = "minCarbohydrates";
    private static final String MAX_CALORIES = "maxCalories";
    private static final String MAX_PROTEINS = "maxProteins";
    private static final String MAX_LIPIDS = "maxLipids";
    private static final String MAX_CARBOHYDRATES = "maxCarbohydrates";

    private static final String REGEX_INDEX = "^[1-9]\\d{0,5}$";

    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if (request.getSession().getAttribute(ERROR) != null) {
            request.setAttribute(ERROR, request.getSession().getAttribute(ERROR));
            request.getSession().setAttribute(ERROR, null);
        }

        int indexOfPage;
        if (request.getParameter(INDEX_OF_PAGE) != null) {
            Pattern pattern = Pattern.compile(REGEX_INDEX);
            Matcher matcher = pattern.matcher(request.getParameter(INDEX_OF_PAGE));

            if(matcher.matches()){
                indexOfPage = Integer.parseInt(request.getParameter(INDEX_OF_PAGE));
            } else {
                request.setAttribute(ERROR, "Error request");
                request.setAttribute(STATUS_CODE, 404);
                return JspPath.ERROR.getUrl();
            }
        } else {
            indexOfPage = 1;
        }

        ProductService productService = new ProductServiceImpl();
        List<Product> products = productService.findProductsByLimit(
                (indexOfPage - 1) * NUMBER_PRODUCTS_PER_PAGE,
                indexOfPage * NUMBER_PRODUCTS_PER_PAGE
        );

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
        request.setAttribute(INDEX_OF_PAGE, indexOfPage);
        request.setAttribute(PRODUCT_LIST_SIZE, productService.takeAllProducts().size());
        request.setAttribute(COMMAND_VALUE, VISIT_PRODUCT_LIST_COMMAND);
        request.setAttribute(PRODUCTS_PER_PAGE, NUMBER_PRODUCTS_PER_PAGE);

        return JspPath.PRODUCT_LIST.getUrl();
    }
}
