package by.talstaya.crackertracker.command.impl.administrator;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.CommentForUserService;
import by.talstaya.crackertracker.service.MealService;
import by.talstaya.crackertracker.service.RatingService;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.CommentForUserServiceImpl;
import by.talstaya.crackertracker.service.impl.MealServiceImpl;
import by.talstaya.crackertracker.service.impl.RatingServiceImpl;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to delete user
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class DeleteUserCommand implements Command {

    private static final String USER = "User";
    private static final String USER_ID = "userId";
    private static final String RESPONSE = "response";
    private static final String USER_LIST_PATH = "/user_list";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        int userId = Integer.parseInt(request.getParameter(USER_ID));

        UserService userService = new UserServiceImpl();

        UserType userType = userService.takeUserType(userId);

        MealService mealService = new MealServiceImpl();
        mealService.deleteMealByUserId(userId);

        CommentForUserService commentForUserService = new CommentForUserServiceImpl();
        commentForUserService.deleteCommentsForUser(userId);

        if(userType.equals(UserType.SUPERVISOR)) {
            userService.deleteAllRequestsForSupervisor(userId);
            userService.deleteAllSupervisorIdBySupervisor(userId);

            RatingService ratingService = new RatingServiceImpl();
            ratingService.deleteSupervisor(userId);
        }

        userService.deleteUser(userId);

        request.setAttribute(RESPONSE, true);
        return request.getContextPath() + USER_LIST_PATH;
    }
}
