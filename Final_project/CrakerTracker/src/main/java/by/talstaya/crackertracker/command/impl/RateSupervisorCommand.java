package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.RatingService;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.RatingServiceImpl;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RateSupervisorCommand implements Command {

    private final String USER = "User";
    private final String SUPERVISOR_ID = "supervisorId";
    private final String RATING = "rating";
    private final String RATED = "rated";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User)request.getSession().getAttribute(USER);
        int supervisorId = Integer.parseInt(request.getParameter(SUPERVISOR_ID));
        int rating = Integer.parseInt(request.getParameter(RATING));

        RatingService ratingService = new RatingServiceImpl();
        ratingService.updateRating(user.getUserId(), supervisorId, rating);
        double averageRating = Math.round(ratingService.takeAverageRating(supervisorId) * 10) / 10.0;

        UserService userService = new UserServiceImpl();
        userService.updateRating(supervisorId, averageRating);

        request.setAttribute(RATED, true);

        return new ShowSupervisorCommand().execute(request, response);
    }
}
