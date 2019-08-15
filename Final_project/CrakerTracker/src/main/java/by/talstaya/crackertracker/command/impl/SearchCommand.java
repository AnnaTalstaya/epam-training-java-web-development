package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;
import by.talstaya.crackertracker.validator.ProductDataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class SearchCommand implements Command {

    private static final String NAME_OR_WORD_IN_NAME = "nameOrWordInName";
    private static final String ERROR = "error";
    private static final String SEARCH = "search";
    private static final String STATUS_CODE = "statusCode";

    private List<UserType> userTypeList;

    public SearchCommand() {
        userTypeList = Arrays.asList(UserType.ANONYMOUS, UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String nameOrWordInName = request.getParameter(NAME_OR_WORD_IN_NAME);

        if(nameOrWordInName != null) {
            nameOrWordInName = nameOrWordInName.trim();
            boolean dataIsCorrect = new ProductDataValidator().validateData(nameOrWordInName);

            if (!dataIsCorrect){
                request.setAttribute(ERROR, "Length of search query is too big");
                request.setAttribute(STATUS_CODE, 404);
                return JspPath.ERROR.getUrl();

            } else {
                ProductService productService = new ProductServiceImpl();

                List<Product> products;
                if(nameOrWordInName.isEmpty()){
                    products = productService.takeAllProducts();
                }else{
                    products = productService.findByNameOrWordInName(nameOrWordInName);
                }

                request.setAttribute(SEARCH, products);
                request.setAttribute(NAME_OR_WORD_IN_NAME, nameOrWordInName);
            }

            return new ProductListCommand().execute(request, response);
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            return JspPath.ERROR.getUrl();
        }
    }
}
