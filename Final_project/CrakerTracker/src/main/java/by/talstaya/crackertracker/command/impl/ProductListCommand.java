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

/**
 * This class is used to show all products
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ProductListCommand implements Command {

    private static final String SEARCH_ERROR = "searchError";
    private static final String SEARCH = "search";

    private static final String PRODUCTS = "products";

    private static final int NUMBER_PRODUCTS_PER_PAGE = 8;

    private static final String PRODUCTS_PER_PAGE = "productsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String PRODUCT_LIST_SIZE = "productListSize";
    private static final String PRODUCT_LIST_COMMAND = "product_list";
    private static final String COMMAND_VALUE = "commandValue";

    private static final String NAME_OR_WORD_IN_NAME = "nameOrWordInName";
    private static final String MIN_CALORIES = "minCalories";
    private static final String MIN_PROTEINS = "minProteins";
    private static final String MIN_LIPIDS = "minLipids";
    private static final String MIN_CARBOHYDRATES = "minCarbohydrates";
    private static final String MAX_CALORIES = "maxCalories";
    private static final String MAX_PROTEINS = "maxProteins";
    private static final String MAX_LIPIDS = "maxLipids";
    private static final String MAX_CARBOHYDRATES = "maxCarbohydrates";

    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int indexOfPage;
        if (request.getParameter(INDEX_OF_PAGE) != null) {
            indexOfPage = Integer.parseInt(request.getParameter(INDEX_OF_PAGE));
        } else {
            indexOfPage = 1;
        }

        ProductService productService = new ProductServiceImpl();

        List<Product> products;
        if (request.getAttribute(SEARCH) != null) { //if we search products by header, SEARCH - found products
            products = (List<Product>) request.getAttribute(SEARCH);
            if (!products.isEmpty()) {
                attributeSettingWithDataFromDb(request, productService);
            } else {
                request.setAttribute(NAME_OR_WORD_IN_NAME, request.getParameter(NAME_OR_WORD_IN_NAME));
                attributeSettingWithDataFromDb(request, productService);
            }
        } else {
            if (request.getParameter(NAME_OR_WORD_IN_NAME) != null
                    && request.getParameter(MIN_CALORIES) != null
                    && request.getParameter(MIN_PROTEINS) != null
                    && request.getParameter(MIN_LIPIDS) != null
                    && request.getParameter(MIN_CARBOHYDRATES) != null
                    && request.getParameter(MAX_CALORIES) != null
                    && request.getParameter(MAX_PROTEINS) != null
                    && request.getParameter(MAX_LIPIDS) != null
                    && request.getParameter(MAX_CARBOHYDRATES) != null) {

                String nameOrWordInName = request.getParameter(NAME_OR_WORD_IN_NAME);
                String strMinCalories = request.getParameter(MIN_CALORIES);
                String strMinProteins = request.getParameter(MIN_PROTEINS);
                String strMinLipids = request.getParameter(MIN_LIPIDS);
                String strMinCarbohydrates = request.getParameter(MIN_CARBOHYDRATES);

                String strMaxCalories = request.getParameter(MAX_CALORIES);
                String strMaxProteins = request.getParameter(MAX_PROTEINS);
                String strMaxLipids = request.getParameter(MAX_LIPIDS);
                String strMaxCarbohydrates = request.getParameter(MAX_CARBOHYDRATES);

                ProductDataValidator productDataValidator = new ProductDataValidator();

                if (productDataValidator.validateData(nameOrWordInName,
                        strMinCalories, strMaxCalories,
                        strMinProteins, strMaxProteins,
                        strMinLipids, strMaxLipids,
                        strMinCarbohydrates, strMaxCarbohydrates
                )) {
                    int minCalories = productService.checkCalories(Integer.parseInt(strMinCalories));
                    int minProteins = productService.checkProteins(Integer.parseInt(strMinProteins));
                    int minLipids = productService.checkLipids(Integer.parseInt(strMinLipids));
                    int minCarbohydrates = productService.checkCarbohydrates(Integer.parseInt(strMinCarbohydrates));

                    int maxCalories = productService.checkCalories(Integer.parseInt(strMaxCalories));
                    int maxProteins = productService.checkProteins(Integer.parseInt(strMaxProteins));
                    int maxLipids = productService.checkLipids(Integer.parseInt(strMaxLipids));
                    int maxCarbohydrates = productService.checkCarbohydrates(Integer.parseInt(strMaxCarbohydrates));


                    if (minCalories > maxCalories) {
                        minCalories = maxCalories;
                    }
                    if (minProteins > maxProteins) {
                        minProteins = maxProteins;
                    }
                    if (minLipids > maxLipids) {
                        minLipids = maxLipids;
                    }
                    if (minCarbohydrates > maxCarbohydrates) {
                        minCarbohydrates = maxCarbohydrates;
                    }

                    products = productService.findProductsByFilterWithLimit(
                            nameOrWordInName,
                            minCalories, maxCalories,
                            minProteins, maxProteins,
                            minLipids, maxLipids,
                            minCarbohydrates, maxCarbohydrates,
                            (indexOfPage - 1) * NUMBER_PRODUCTS_PER_PAGE,
                            indexOfPage * NUMBER_PRODUCTS_PER_PAGE
                    );

                    request.setAttribute(COMMAND_VALUE, PRODUCT_LIST_COMMAND);
                    request.setAttribute(PRODUCT_LIST_SIZE, productService.findAllProductsByFilter(nameOrWordInName,
                            minCalories, maxCalories,
                            minProteins, maxProteins,
                            minLipids, maxLipids,
                            minCarbohydrates, maxCarbohydrates).size());

                    request.setAttribute(NAME_OR_WORD_IN_NAME, nameOrWordInName);
                    request.setAttribute(MIN_CALORIES, minCalories);
                    request.setAttribute(MIN_PROTEINS, minProteins);
                    request.setAttribute(MIN_LIPIDS, minLipids);
                    request.setAttribute(MIN_CARBOHYDRATES, minCarbohydrates);

                    request.setAttribute(MAX_CALORIES, maxCalories);
                    request.setAttribute(MAX_PROTEINS, maxProteins);
                    request.setAttribute(MAX_LIPIDS, maxLipids);
                    request.setAttribute(MAX_CARBOHYDRATES, maxCarbohydrates);
                } else {
                    request.setAttribute(ERROR, "Error request");
                    request.setAttribute(STATUS_CODE, 404);
                    return JspPath.ERROR.getUrl();
                }

            } else {
                request.setAttribute(ERROR, "Error request");
                request.setAttribute(STATUS_CODE, 404);
                return JspPath.ERROR.getUrl();
            }
        }

        if (products.isEmpty()) {
            request.setAttribute(SEARCH_ERROR, "Product not found");
        } else {
            request.setAttribute(PRODUCTS, products);
        }

        request.setAttribute(INDEX_OF_PAGE, indexOfPage);
        request.setAttribute(PRODUCTS_PER_PAGE, NUMBER_PRODUCTS_PER_PAGE);

        return JspPath.PRODUCT_LIST.getUrl();
    }

    private void attributeSettingWithDataFromDb(HttpServletRequest request, ProductService productService) throws ServiceException {
        request.setAttribute(MIN_CALORIES, productService.findMinCalories());
        request.setAttribute(MIN_PROTEINS, productService.findMinProteins());
        request.setAttribute(MIN_LIPIDS, productService.findMinLipids());
        request.setAttribute(MIN_CARBOHYDRATES, productService.findMinCarbohydrates());

        request.setAttribute(MAX_CALORIES, productService.findMaxCalories());
        request.setAttribute(MAX_PROTEINS, productService.findMaxProteins());
        request.setAttribute(MAX_LIPIDS, productService.findMaxLipids());
        request.setAttribute(MAX_CARBOHYDRATES, productService.findMaxCarbohydrates());
    }

}

