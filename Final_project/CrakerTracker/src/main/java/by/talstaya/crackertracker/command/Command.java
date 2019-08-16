package by.talstaya.crackertracker.command;

import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This interface is used to process the request and response
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface Command {

    /**
     * This method gives all types of users that can use this command
     * @return all types of users that can use this command
     */
    List<UserType> getUserTypeList();

    /**
     * This method processes the request and response
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
