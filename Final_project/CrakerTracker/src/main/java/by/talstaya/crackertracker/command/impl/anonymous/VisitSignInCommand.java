package by.talstaya.crackertracker.command.impl.anonymous;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used for to visit sign in page with default params
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class VisitSignInCommand implements Command {

    private static final String ERROR = "error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if (request.getSession().getAttribute(ERROR) != null) {
            request.setAttribute(ERROR, request.getSession().getAttribute(ERROR));
            request.getSession().setAttribute(ERROR, null);
        }

        return JspPath.SIGN_IN.getUrl();
    }
}
