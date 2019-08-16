package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.entity.CommentForUser;
import by.talstaya.crackertracker.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

/**
 * This class is a layer for interacting with CommentForUserDao
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface CommentForUserService {

    List<CommentForUser> findComments(int userId, String mealDate) throws ServiceException;

    void insertComment(CommentForUser commentForUser) throws ServiceException;

    void deleteComment(int commentId) throws ServiceException;

    void deleteCommentsForUser(int userId) throws ServiceException;

    void deleteCommentsByCommentator(int commentatorId) throws ServiceException;

    void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws ServiceException;

}
