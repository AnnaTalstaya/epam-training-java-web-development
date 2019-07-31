package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.command.JspPath;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.RatingService;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.RatingServiceImpl;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSupervisorCommand implements Command {

    private final String USER = "User";
    private final String SUPERVISOR = "supervisor";
    private final String RATING = "rating";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User)request.getSession().getAttribute(USER);
        UserService userService = new UserServiceImpl();

        User supervisor = userService.findSupervisorOfUser(user.getUserId());

        if (supervisor != null) {
            request.setAttribute(SUPERVISOR, supervisor);
            if(user.getSupervisorId() != supervisor.getUserId()){
                user.setSupervisorId(supervisor.getUserId());
                request.getSession().setAttribute(USER, user);
            }

            RatingService ratingService = new RatingServiceImpl();
            int ratingByUser = (int)ratingService.takeRatingByUser(user.getUserId());
            if(ratingByUser != 0) {
                request.setAttribute(RATING, ratingByUser);
            }
        }

        return JspPath.SUPERVISOR.getUrl();
    }
}
