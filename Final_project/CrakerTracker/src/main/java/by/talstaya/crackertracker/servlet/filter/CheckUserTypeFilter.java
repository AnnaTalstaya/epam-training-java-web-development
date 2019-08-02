package by.talstaya.crackertracker.servlet.filter;

import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" })
public class CheckUserTypeFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String USER = "User";

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if(request instanceof HttpServletRequest) {
            try {
                HttpServletRequest httpRequest = (HttpServletRequest) request;

                User currentUser = (User)httpRequest.getSession().getAttribute(USER);

                if(currentUser != null) {
                    UserService userService = new UserServiceImpl();
                    User currentUserFromDb = userService.findUserById(currentUser.getUserId());

                    if(currentUser.getUserType() != currentUserFromDb.getUserType()) {
                        httpRequest.getSession().setAttribute(USER, currentUserFromDb);
                    }
                }

            } catch (ServiceException e) {
                e.printStackTrace(); //log
            }

            chain.doFilter(request, response);
        } else {
            LOGGER.error("You are trying to use HTTP filter for not HTTP request");
        }

    }


    public void destroy() {
    }

}
