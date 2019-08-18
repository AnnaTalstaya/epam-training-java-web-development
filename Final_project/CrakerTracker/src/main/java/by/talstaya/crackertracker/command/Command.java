package by.talstaya.crackertracker.command;

import by.talstaya.crackertracker.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface is used to process the request and response
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface Command {

    /**
     * This method processes the request and response
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
