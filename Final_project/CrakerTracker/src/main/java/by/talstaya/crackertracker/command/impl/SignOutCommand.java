package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to sign out
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class SignOutCommand implements Command {

    private static final String USER = "User";
    private static final String RESPONSE = "response";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute(USER, null);
        request.setAttribute(RESPONSE, true);
        
        return request.getContextPath();
    }
}
