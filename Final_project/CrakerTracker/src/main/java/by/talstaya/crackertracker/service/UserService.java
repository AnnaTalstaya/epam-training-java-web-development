package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.entity.UserType;
import by.talstaya.crackertracker.exception.ServiceException;

import java.util.List;

/**
 * This class is a layer for interacting with UserDao
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface UserService {

    List<User> findByUsernameAndPass(String username, String password) throws ServiceException;

    List<User> findByEmailAndPass(String email, String password) throws ServiceException;

    List<User> findAllSupervisors() throws ServiceException;

    List<User> findAllSupervisorsWithLimit(int startIndex, int endIndex) throws ServiceException;

    List<User> findUsersOfSupervisor(int supervisorId) throws ServiceException;

    List<User> findUsersOfSupervisorWithLimit(int supervisorId, int startIndex, int endIndex) throws ServiceException;

    User findUserById(int userId) throws ServiceException;

    int findRequestedSupervisorId(int userId) throws ServiceException;

    User findSupervisorOfUser(int userId) throws ServiceException;

    void insertUser(User user) throws ServiceException;

    void deleteUser(int userId) throws ServiceException;

    boolean containsEmail(String email) throws ServiceException;

    boolean containsUsername(String username) throws ServiceException;

    void update(User user);

    void updateUserType(int userId, String userType) throws ServiceException;

    void putRequestForSupervisor(int userId, int supervisorId) throws ServiceException;

    boolean containsSupervisorOrRequestForSupervisor(int userId) throws ServiceException;

    void deleteRequestForSupervisor(int userId) throws ServiceException;

    void deleteUserOfSupervisor(int supervisorId, int userId) throws ServiceException;

    void deleteSupervisorOfUser(int userId) throws ServiceException;

    List<User> takeAllUsers() throws ServiceException;

    List<User> takeAllUsersWithLimit(int startIndex, int endIndex) throws ServiceException;

    List<User> findRequestsForSupervisor(int supervisorId) throws ServiceException;

    List<User> findRequestsForSupervisorWithLimit(int supervisorId, int startIndex, int endIndex) throws ServiceException;

    void supervisorAcceptsRequestFromUser(int supervisorId, int userId) throws ServiceException;

    void supervisorRejectsRequestFromUser(int supervisorId, int userId) throws ServiceException;

    void updateRating(int supervisorId, double averageRating) throws ServiceException;

    UserType takeUserType(int userId) throws ServiceException;

    void deleteAllRequestsForSupervisor(int supervisorId) throws ServiceException;

    void deleteAllSupervisorIdBySupervisor(int supervisorId) throws ServiceException;

}
