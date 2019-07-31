package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.entity.CommentForUser;
import by.talstaya.crackertracker.exception.ServiceException;

import java.util.List;

public interface CommentForUserService {

    List<CommentForUser> findComments(int userId, String mealDate) throws ServiceException;

    void insertComment(CommentForUser commentForUser) throws ServiceException;

    void deleteComment(int commentId) throws ServiceException;

}
