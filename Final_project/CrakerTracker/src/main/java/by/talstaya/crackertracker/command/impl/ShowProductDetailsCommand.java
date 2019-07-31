package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShowProductDetailsCommand implements Command {

    private ResourceBundle rb;
    private final int startQuantity = 1;

    private final String PRODUCT_ID = "productId";
    private final String ERROR = "error";
    private final String PRODUCT = "product";
    private final String QUANTITY = "quantity";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Locale locale = new Locale(String.valueOf(request.getSession().getAttribute("locale")));
        rb = ResourceBundle.getBundle("message", locale);

        int productId = Integer.parseInt(request.getParameter(PRODUCT_ID));

        if(productId != 0){
            ProductService productService = new ProductServiceImpl();
            Product product = productService.findByProductId(productId);

            if(product == null){
                request.setAttribute(ERROR, rb.getString("product.error.product_id_not_found"));
                return JspPath.ERROR.getUrl();
            } else{
                request.setAttribute(PRODUCT, product);
                request.setAttribute(QUANTITY, startQuantity);

            }
        } else {
            request.setAttribute(ERROR, rb.getString("product.error.not_found"));
            return JspPath.ERROR.getUrl();
        }
        return JspPath.PRODUCT.getUrl();
    }
}
