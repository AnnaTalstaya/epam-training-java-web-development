package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.CommentForUser;
import by.talstaya.crackertracker.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface CommentForUserDao extends BasicDao<CommentForUser> {

    List<CommentForUser> findComments(int userId, String mealDate) throws DaoException;

    void deleteCommentsForUser(int userId) throws DaoException;

    void deleteCommentsByCommentator(int commentatorId) throws DaoException;

    void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws DaoException;

}
