package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to show product details
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ShowProductDetailsCommand implements Command {

    private static final int startQuantity = 1;

    private static final String PRODUCT_ID = "productId";
    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";
    private static final String PRODUCT = "product";
    private static final String QUANTITY = "quantity";
    private static final String OK = "ok";

    private static final String REGEX_ID = "^[1-9]\\d*$";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if (request.getSession().getAttribute(OK) != null) {
            request.setAttribute(OK, request.getSession().getAttribute(OK));
            request.getSession().setAttribute(OK, null);
        }

        String page;

        if (request.getParameter(PRODUCT_ID) != null) {

            Pattern pattern = Pattern.compile(REGEX_ID);
            Matcher matcher = pattern.matcher(request.getParameter(PRODUCT_ID));

            if (matcher.matches()) {
                int productId = Integer.parseInt(request.getParameter(PRODUCT_ID));

                ProductService productService = new ProductServiceImpl();
                Product product = productService.findByProductId(productId);

                if (product == null) {
                    request.setAttribute(ERROR, "Product with such id not found");
                    request.setAttribute(STATUS_CODE, 404);
                    page = JspPath.ERROR.getUrl();
                } else {
                    request.setAttribute(PRODUCT, product);
                    request.setAttribute(QUANTITY, startQuantity);

                    page = JspPath.PRODUCT.getUrl();
                }

            } else {
                request.setAttribute(ERROR, "Error request");
                request.setAttribute(STATUS_CODE, 404);
                page = JspPath.ERROR.getUrl();
            }
        } else {
            request.setAttribute(ERROR, "Error request");
            request.setAttribute(STATUS_CODE, 404);
            page = JspPath.ERROR.getUrl();
        }

        return page;
    }
}
