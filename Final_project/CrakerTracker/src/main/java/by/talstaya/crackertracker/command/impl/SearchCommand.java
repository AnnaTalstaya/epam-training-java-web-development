package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;
import by.talstaya.crackertracker.validator.ProductDataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to search product
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class SearchCommand implements Command {

    private static final int NUMBER_PRODUCTS_PER_PAGE = 8;

    private static final String NAME_OR_WORD_IN_NAME = "nameOrWordInName";
    private static final String SEARCH = "search";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    private static final String COMMAND_VALUE = "commandValue";
    private static final String SEARCH_COMMAND = "search";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String PRODUCT_LIST_SIZE = "productListSize";

    private static final String REGEX_INDEX = "^[1-9]\\d{0,5}$";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

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

        String nameOrWordInName = request.getParameter(NAME_OR_WORD_IN_NAME);

        if (nameOrWordInName != null) {
            nameOrWordInName = nameOrWordInName.trim();
            boolean dataIsCorrect = new ProductDataValidator().validateData(nameOrWordInName);

            if (!dataIsCorrect) {
                request.setAttribute(ERROR, "Length of search query is too big");
                request.setAttribute(STATUS_CODE, 404);
                return JspPath.ERROR.getUrl();

            } else {
                ProductService productService = new ProductServiceImpl();

                List<Product> products;
                if (nameOrWordInName.isEmpty()) {
                    products = productService.findProductsByLimit(
                            (indexOfPage - 1) * NUMBER_PRODUCTS_PER_PAGE,
                            indexOfPage * NUMBER_PRODUCTS_PER_PAGE);
                    request.setAttribute(PRODUCT_LIST_SIZE, productService.takeAllProducts().size());

                } else {
                    products = productService.findByNameOrWordInNameWithLimit(nameOrWordInName,
                            (indexOfPage - 1) * NUMBER_PRODUCTS_PER_PAGE,
                            indexOfPage * NUMBER_PRODUCTS_PER_PAGE);
                    request.setAttribute(PRODUCT_LIST_SIZE, productService.findByNameOrWordInName(nameOrWordInName).size());
                }

                request.setAttribute(SEARCH, products);
                request.setAttribute(NAME_OR_WORD_IN_NAME, nameOrWordInName);
                request.setAttribute(COMMAND_VALUE, SEARCH_COMMAND);

            }

            return new ProductListCommand().execute(request, response);
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return JspPath.ERROR.getUrl();
        }
    }
}
