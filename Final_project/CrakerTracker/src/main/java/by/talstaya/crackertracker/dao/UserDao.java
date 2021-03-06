package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.DaoException;

import java.util.List;

/**
 * This class is a layer for interacting with user database
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface UserDao extends BasicDao<User> {

    List<User> takeAllUsers() throws DaoException;

    List<User> takeAllUsersWithLimit(int startIndex, int endIndex) throws DaoException;

    List<User> findByUsernameAndPass(String username, String password) throws DaoException;

    List<User> findByEmailAndPass(String email, String password) throws DaoException;

    List<User> findAllSupervisors() throws DaoException;

    List<User> findAllSupervisorsWithLimit(int startIndex, int endIndex) throws DaoException;

    List<User> findUsersOfSupervisor(int supervisorId) throws DaoException;

    List<User> findUsersOfSupervisorWithLimit(int supervisorId, int startIndex, int endIndex) throws DaoException;

    List<User> findRequestsForSupervisor(int supervisorId) throws DaoException;

    List<User> findRequestsForSupervisorWithLimit(int supervisorId, int startIndex, int endIndex) throws DaoException;

    int findRequestedSupervisorId(int userId) throws DaoException;

    int findSupervisorId(int userId) throws DaoException;

    void updateUserType(int userId, String userType) throws DaoException;

    void updateRating(int supervisorId, double averageRating) throws DaoException;

    boolean containsEmail(String email) throws DaoException;

    boolean containsUsername(String username) throws DaoException;

    void deleteUserOfSupervisor(int supervisorId, int userId) throws DaoException;

    void supervisorAcceptsRequestFromUser(int supervisorId, int userId) throws DaoException;

    void putRequestForSupervisor(int userId, int supervisorId) throws DaoException;

    void updateSupervisorId(int supervisorId, int userId) throws DaoException;

    boolean containsSupervisor(int userId) throws DaoException;

    boolean containsRequestForSupervisor(int userId) throws DaoException;

    void deleteRequestForSupervisor(int userId) throws DaoException;

    UserType takeUserType(int userId) throws DaoException;

    void deleteAllRequestsForSupervisor(int supervisorId) throws DaoException;

    void deleteAllSupervisorIdBySupervisor(int supervisorId) throws DaoException;

}
