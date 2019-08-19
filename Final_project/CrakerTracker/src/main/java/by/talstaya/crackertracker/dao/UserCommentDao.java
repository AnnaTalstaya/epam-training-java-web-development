package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.UserComment;
import by.talstaya.crackertracker.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

/**
 * This class is a layer for interacting with comments database
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface UserCommentDao extends BasicDao<UserComment> {

    List<UserComment> findComments(int userId, String mealDate) throws DaoException;

    void deleteCommentsForUser(int userId) throws DaoException;

    void deleteCommentsByCommentator(int commentatorId) throws DaoException;

    void deleteCommentsForUserByDate(int userId, LocalDate selectedDate) throws DaoException;

}
