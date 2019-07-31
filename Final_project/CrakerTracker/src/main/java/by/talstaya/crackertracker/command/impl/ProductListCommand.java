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

public class ProductListCommand implements Command {

    private static final int NUMBER_PRODUCTS_PER_PAGE = 8;

    private static final String SEARCH_ERROR = "searchError";
    private static final String SEARCH = "search";

    private static final String PRODUCTS_PER_PAGE = "productsPerPage";
    private static final String INDEX_OF_PAGE = "indexOfPage";
    private static final String START_INDEX_OF_PRODUCT_LIST = "startIndexOfProductList";
    private static final String PRODUCTS = "products";
    private static final String CHANGE_PAGE = "changePage";

    private static final String MIN_CALORIES = "minCalories";
    private static final String MIN_PROTEINS = "minProteins";
    private static final String MIN_LIPIDS = "minLipids";
    private static final String MIN_CARBOHYDRATES = "minCarbohydrates";

    private static final String MAX_CALORIES = "maxCalories";
    private static final String MAX_PROTEINS = "maxProteins";
    private static final String MAX_LIPIDS = "maxLipids";
    private static final String MAX_CARBOHYDRATES = "maxCarbohydrates";

    private static final String NAME_OR_WORD_IN_NAME = "nameOrWordInName";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        ProductService productService = new ProductServiceImpl();

            int productsPerPage;
            if (request.getParameter(PRODUCTS_PER_PAGE) == null) {  //if we open the jsp the first time
                productsPerPage = NUMBER_PRODUCTS_PER_PAGE;
            } else {
                productsPerPage = Integer.parseInt(request.getParameter(PRODUCTS_PER_PAGE));
            }

            int newIndex;
            if (request.getParameter(INDEX_OF_PAGE) == null) {
                newIndex = 1;
            } else {
                newIndex = (int) Double.parseDouble(request.getParameter(INDEX_OF_PAGE))
                        + Integer.parseInt(request.getParameter(CHANGE_PAGE));
            }

            List<Product> products;
            if (request.getAttribute(SEARCH) != null) {
                products = (List<Product>) request.getAttribute(SEARCH);
                if (!products.isEmpty()) {
                    request.setAttribute(MIN_CALORIES, productService.findMinCalories());
                    request.setAttribute(MIN_PROTEINS, productService.findMinProteins());
                    request.setAttribute(MIN_LIPIDS, productService.findMinLipids());
                    request.setAttribute(MIN_CARBOHYDRATES, productService.findMinCarbohydrates());

                    request.setAttribute(MAX_CALORIES, productService.findMaxCalories());
                    request.setAttribute(MAX_PROTEINS, productService.findMaxProteins());
                    request.setAttribute(MAX_LIPIDS, productService.findMaxLipids());
                    request.setAttribute(MAX_CARBOHYDRATES, productService.findMaxCarbohydrates());
                }else{
                    request.setAttribute(NAME_OR_WORD_IN_NAME, request.getParameter(NAME_OR_WORD_IN_NAME));
                    request.setAttribute(MIN_CALORIES, productService.findMinCalories());
                    request.setAttribute(MIN_PROTEINS, productService.findMinProteins());
                    request.setAttribute(MIN_LIPIDS, productService.findMinLipids());
                    request.setAttribute(MIN_CARBOHYDRATES, productService.findMinCarbohydrates());

                    request.setAttribute(MAX_CALORIES, productService.findMaxCalories());
                    request.setAttribute(MAX_PROTEINS, productService.findMaxProteins());
                    request.setAttribute(MAX_LIPIDS, productService.findMaxLipids());
                    request.setAttribute(MAX_CARBOHYDRATES, productService.findMaxCarbohydrates());

                    request.setAttribute(SEARCH_ERROR, "Product not found");

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

                    int minCalories = Integer.parseInt(request.getParameter(MIN_CALORIES));
                    int minProteins = Integer.parseInt(request.getParameter(MIN_PROTEINS));
                    int minLipids = Integer.parseInt(request.getParameter(MIN_LIPIDS));
                    int minCarbohydrates = Integer.parseInt(request.getParameter(MIN_CARBOHYDRATES));

                    int maxCalories = Integer.parseInt(request.getParameter(MAX_CALORIES));
                    int maxProteins = Integer.parseInt(request.getParameter(MAX_PROTEINS));
                    int maxLipids = Integer.parseInt(request.getParameter(MAX_LIPIDS));
                    int maxCarbohydrates = Integer.parseInt(request.getParameter(MAX_CARBOHYDRATES));

                    products = productService.findProductsByFilter(
                            nameOrWordInName,
                            minCalories, maxCalories,
                            minProteins, maxProteins,
                            minLipids, maxLipids,
                            minCarbohydrates, maxCarbohydrates);

                    request.setAttribute(NAME_OR_WORD_IN_NAME, nameOrWordInName);
                    request.setAttribute(MIN_CALORIES, minCalories);
                    request.setAttribute(MIN_PROTEINS, minProteins);
                    request.setAttribute(MIN_LIPIDS, minLipids);
                    request.setAttribute(MIN_CARBOHYDRATES, minCarbohydrates);

                    request.setAttribute(MAX_CALORIES, maxCalories);
                    request.setAttribute(MAX_PROTEINS, maxProteins);
                    request.setAttribute(MAX_LIPIDS, maxLipids);
                    request.setAttribute(MAX_CARBOHYDRATES, maxCarbohydrates);

                } else {   //if page is opened for the first time
                    products = productService.takeAllProducts();

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
                }

            }

            request.setAttribute(PRODUCTS_PER_PAGE, productsPerPage);
            request.setAttribute(START_INDEX_OF_PRODUCT_LIST, (newIndex - 1) * productsPerPage);
            request.setAttribute(INDEX_OF_PAGE, newIndex);
            if(products.isEmpty()){
                request.setAttribute(SEARCH_ERROR, "Product not found");
            }else {
                request.setAttribute(PRODUCTS, products);
            }


        return JspPath.PRODUCT_LIST.getUrl();
    }
}

