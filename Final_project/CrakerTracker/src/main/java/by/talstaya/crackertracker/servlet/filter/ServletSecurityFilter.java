package by.talstaya.crackertracker.servlet.filter;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.CommandFactory;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ServletSecurityFilter is used to prohibits users from visiting pages to which they do not have rights
 *
 * @author Anna Talstaya
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/*"})
public class ServletSecurityFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String USER = "User";
    private static final String COMMAND = "command";
    private static final String ERROR = "error";

    private static final String SIGN_IN_PATH = "/sign_in";
    private static final String PRODUCT_LIST_PATH = "/visit_product_list";

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            User user = (User) httpRequest.getSession().getAttribute(USER);

            CommandFactory commandFactory = CommandFactory.getInstance();

            if (httpRequest.getAttribute(COMMAND) != null) {
                Command command = commandFactory.receiveCommand((String) httpRequest.getAttribute(COMMAND));
                if (user != null) {
                    if (command.getUserTypeList().contains(user.getUserType())) {
                        chain.doFilter(request, response);
                    } else {
                        httpRequest.getSession().setAttribute(ERROR, "Sorry! You have no rights to visit this page.");
                        httpResponse.sendRedirect(httpRequest.getContextPath() + PRODUCT_LIST_PATH);
                    }
                } else {
                    if (command.getUserTypeList().contains(UserType.ANONYMOUS)) {
                        chain.doFilter(request, response);
                    } else {
                        httpRequest.getSession().setAttribute(ERROR, "Sign in for visiting this page");
                        httpResponse.sendRedirect(httpRequest.getContextPath() + SIGN_IN_PATH);
                    }
                }
            } else {
                chain.doFilter(request, response);
            }


        } else {
            LOGGER.error("You are trying to use HTTP filter for not HTTP request and/or response");
        }
    }

    public void destroy() {
    }

}
