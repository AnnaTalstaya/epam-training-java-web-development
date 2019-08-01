package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class ShowProductDetailsCommand implements Command {

    private static final int startQuantity = 1;

    private static final String PRODUCT_ID = "productId";
    private static final String ERROR = "error";
    private static final String PRODUCT = "product";
    private static final String QUANTITY = "quantity";

    private List<UserType> userTypeList;

    public ShowProductDetailsCommand() {
        userTypeList = Arrays.asList(UserType.ANONYMOUS, UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR);
    }

    @Override
    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int productId = Integer.parseInt(request.getParameter(PRODUCT_ID));

        if(productId != 0){
            ProductService productService = new ProductServiceImpl();
            Product product = productService.findByProductId(productId);

            if(product == null){
                request.setAttribute(ERROR, "Product with such id not found");
                return JspPath.ERROR.getUrl();
            } else{
                request.setAttribute(PRODUCT, product);
                request.setAttribute(QUANTITY, startQuantity);

            }
        } else {
            request.setAttribute(ERROR, "Product not found");
            return JspPath.ERROR.getUrl();
        }
        return JspPath.PRODUCT.getUrl();
    }
}
