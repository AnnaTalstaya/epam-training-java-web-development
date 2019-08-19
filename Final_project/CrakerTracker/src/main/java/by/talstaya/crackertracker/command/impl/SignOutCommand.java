package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to sign out
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class SignOutCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String USER = "User";
    private static final String RESPONSE = "response";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOGGER.info("User " + ((User)request.getSession().getAttribute(USER)).getUsername() + " signed out.");

        request.getSession().setAttribute(USER, null);
        request.setAttribute(RESPONSE, true);
        
        return request.getContextPath();
    }
}
