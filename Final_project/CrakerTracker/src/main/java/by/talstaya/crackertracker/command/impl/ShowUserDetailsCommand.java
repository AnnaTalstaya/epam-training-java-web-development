package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShowUserDetailsCommand implements Command {

    private ResourceBundle rb;

    private final String USER_ID = "userId";
    private final String ERROR = "error";
    private final String USER = "user";
    private final String GREATER_THAN_ONE_ADMIN = "greaterThanOneAdmin";



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Locale locale = new Locale(String.valueOf(request.getSession().getAttribute("locale")));
        rb = ResourceBundle.getBundle("message", locale);

        int userId = Integer.parseInt(request.getParameter(USER_ID));
        boolean greaterThanOneAdmin = Boolean.parseBoolean(request.getParameter(GREATER_THAN_ONE_ADMIN));

        if(userId != 0){

            UserService userService = new UserServiceImpl();
            User user = userService.findUserById(userId);

            if(user == null){
                request.setAttribute(ERROR, rb.getString("user.error.user_id_not_found"));
                return JspPath.ERROR.getUrl();
            } else{
                request.setAttribute(USER, user);
                request.setAttribute(GREATER_THAN_ONE_ADMIN, greaterThanOneAdmin);
            }
        } else {
            request.setAttribute(ERROR, rb.getString("user.error.not_found"));
            return JspPath.ERROR.getUrl();
        }
        return JspPath.USER.getUrl();
    }
}
