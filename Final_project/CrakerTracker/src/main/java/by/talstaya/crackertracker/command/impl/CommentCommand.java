package by.talstaya.crackertracker.command.impl;

import by.talstaya.crackertracker.command.Command;
import by.talstaya.crackertracker.entity.CommentForUser;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.CommentForUserService;
import by.talstaya.crackertracker.service.impl.CommentForUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentCommand implements Command {

    private static final String USER = "User";
    private static final String MEAL_DATE = "mealDate";
    private static final String USER_ID_FOR_SUPERVISOR = "userIdForSupervisor";
    private static final String COMMENT = "comment";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User) request.getSession().getAttribute(USER);
        String selectedDate = request.getParameter(MEAL_DATE);
        String comment = request.getParameter(COMMENT);

        int userId;
        if (!request.getParameter(USER_ID_FOR_SUPERVISOR).isEmpty()) {
            userId = Integer.parseInt(request.getParameter(USER_ID_FOR_SUPERVISOR));
        } else {
            userId = user.getUserId();
        }

        CommentForUserService commentForUserService = new CommentForUserServiceImpl();
        commentForUserService.insertComment(new CommentForUser.Builder()
                .setMealDate(selectedDate)
                .setUserId(userId)
                .setCommentator(new User.Builder().setUserId(user.getUserId()).build())
                .setComment(comment)
                .build());

        //todo validation of comment



        return new ShowDietCommand().execute(request, response);
    }
}
