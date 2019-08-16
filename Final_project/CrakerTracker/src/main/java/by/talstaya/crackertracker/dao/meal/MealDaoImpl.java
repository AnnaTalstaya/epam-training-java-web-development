package by.talstaya.crackertracker.dao.meal;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.dao.MealDao;
import by.talstaya.crackertracker.entity.Meal;
import by.talstaya.crackertracker.entity.MealTime;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.entity.User;
import by.talstaya.crackertracker.exception.DaoException;
import by.talstaya.crackertracker.exception.ServiceException;
import by.talstaya.crackertracker.service.MealTimeService;
import by.talstaya.crackertracker.service.ProductService;
import by.talstaya.crackertracker.service.UserService;
import by.talstaya.crackertracker.service.impl.MealTimeServiceImpl;
import by.talstaya.crackertracker.service.impl.ProductServiceImpl;
import by.talstaya.crackertracker.service.impl.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MealDaoImpl implements MealDao {

    private static final String SQL_INSERT = "INSERT INTO meals (user_id, product_id, date, meal_time_id, quantity) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_MEAL = "DELETE FROM meals WHERE id=?";
    private static final String SQL_UPDATE_MEAL = "UPDATE meals SET user_id=?, product_id=?, date=?, meal_time_id=?, quantity=? WHERE id=?";
    private static final String SQL_UPDATE_QUANTITY = "UPDATE meals SET quantity=? WHERE id=?";

    private static final String SQL_DELETE_MEAL_BY_USER_ID = "DELETE FROM meals WHERE user_id=?";

    private static final String SQL_FIND_DATE_BY_USER_ID = "SELECT date FROM meals WHERE user_id = ?";
    private static final String SQL_FIND_MEAL_BY_USER_ID_AND_MEAL_DATE_AND_MEAL_TIME =
            "SELECT meals.id, meals.user_id, meals.product_id, meals.date, meals.meal_time_id, quantity" +
                    " FROM meals" +
                    " INNER JOIN meal_time ON meals.meal_time_id = meal_time.id" +
                    " WHERE user_id=? AND date=? AND meal_time=?";

    private static final String SQL_FIND_MEAL_BY_USER_ID_MEAL_DATE_MEAL_TIME_PRODUCT_ID =
            "SELECT id, user_id, product_id, date, meal_time_id, quantity" +
                    " FROM meals" +
                    " WHERE user_id=? AND product_id=? AND date=? AND meal_time_id=?";


    private static final String SQL_COUNT_TOTAL_CALORIES_BY_USER_ID_AND_MEAL_DATE =
            "SELECT SUM(calories*quantity)" +
                    " FROM products" +
                    " INNER JOIN" +
                    " (SELECT product_id, quantity FROM meals WHERE user_id=? AND date=?) meals_id_table" +
                    " ON products.id=meals_id_table.product_id";

    private static final String SQL_COUNT_TOTAL_PROTEINS_BY_USER_ID_AND_MEAL_DATE =
            "SELECT SUM(proteins*quantity)" +
                    " FROM products" +
                    " INNER JOIN" +
                    " (SELECT product_id, quantity FROM meals WHERE user_id=? AND date=?) meals_id_table" +
                    " ON products.id=meals_id_table.product_id";

    private static final String SQL_COUNT_TOTAL_LIPIDS_BY_USER_ID_AND_MEAL_DATE =
            "SELECT SUM(lipids*quantity)" +
                    " FROM products" +
                    " INNER JOIN" +
                    " (SELECT product_id, quantity FROM meals WHERE user_id=? AND date=?) meals_id_table" +
                    " ON products.id=meals_id_table.product_id";

    private static final String SQL_COUNT_TOTAL_CARBOHYDRATES_BY_USER_ID_AND_MEAL_DATE =
            "SELECT SUM(carbohydrates*quantity)" +
                    " FROM products" +
                    " INNER JOIN" +
                    " (SELECT product_id, quantity FROM meals WHERE user_id=? AND date=?) meals_id_table" +
                    " ON products.id=meals_id_table.product_id";

    @Override
    public void insert(Meal meal) throws DaoException {

        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        Meal mealFromDb = findMealByUserIdMealDateMealTimeProductId(meal);
        if (mealFromDb != null) {
            update(new Meal.Builder()
                    .setMealId(mealFromDb.getMealId())
                    .setUser(mealFromDb.getUser())
                    .setProduct(mealFromDb.getProduct())
                    .setDate(mealFromDb.getDate())
                    .setMealTime(mealFromDb.getMealTime())
                    .setQuantity(meal.getQuantity() + mealFromDb.getQuantity())
                    .build());
        } else {
            try {
                preparedStatement = connection.prepareStatement(SQL_INSERT);
                preparedStatement.setInt(1, meal.getUser().getUserId());
                preparedStatement.setInt(2, meal.getProduct().getProductId());
                preparedStatement.setDate(3, Date.valueOf(meal.getDate()));
                preparedStatement.setInt(4, meal.getMealTime().getMealTimeId());
                preparedStatement.setInt(5, meal.getQuantity());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                ConnectionPool.getInstance().returnConnection(connection);
                closePreparedStatement(preparedStatement);
            }
        }
    }


    @Override
    public void delete(int mealId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_MEAL);
            preparedStatement.setInt(1, mealId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(Meal meal) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_MEAL);
            preparedStatement.setInt(1, meal.getUser().getUserId());
            preparedStatement.setInt(2, meal.getProduct().getProductId());
            preparedStatement.setDate(3, Date.valueOf(meal.getDate()));
            preparedStatement.setInt(4, meal.getMealTime().getMealTimeId());
            preparedStatement.setInt(5, meal.getQuantity());
            preparedStatement.setInt(6, meal.getMealId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public Meal findById(int mealId) throws DaoException {
        return null;         //todo

    }

    @Override
    public void updateQuantity(int mealId, int quantity) throws DaoException {

        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_QUANTITY);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, mealId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public Set<String> findDatesByUserId(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        Set<String> dates = new TreeSet<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_DATE_BY_USER_ID);

            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dates.add(resultSet.getString(1));
            }
            return dates;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Meal> findMealsByUserIdAndMealDateAndMealTime(int userId, String mealDate, String mealTime) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Meal> mealList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_MEAL_BY_USER_ID_AND_MEAL_DATE_AND_MEAL_TIME);

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, mealDate);
            preparedStatement.setString(3, mealTime);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mealList.add(new Meal.Builder()
                        .setMealId(resultSet.getInt(1))
                        .setUser(findUserById(resultSet.getInt(2)))
                        .setProduct(findProductById(resultSet.getInt(3)))
                        .setDate(resultSet.getDate(4).toLocalDate())
                        .setMealTime(findMealTimeById(resultSet.getInt(5)))
                        .setQuantity(resultSet.getInt(6))
                        .build());
            }
            return mealList;

        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public Meal findMealByUserIdMealDateMealTimeProductId(Meal meal) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_MEAL_BY_USER_ID_MEAL_DATE_MEAL_TIME_PRODUCT_ID);

            preparedStatement.setInt(1, meal.getUser().getUserId());
            preparedStatement.setInt(2, meal.getProduct().getProductId());
            preparedStatement.setDate(3, Date.valueOf(meal.getDate()));
            preparedStatement.setInt(4, meal.getMealTime().getMealTimeId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Meal.Builder()
                        .setMealId(resultSet.getInt(1))
                        .setUser(findUserById(resultSet.getInt(2)))
                        .setProduct(findProductById(resultSet.getInt(3)))
                        .setDate(resultSet.getDate(4).toLocalDate())
                        .setMealTime(findMealTimeById(resultSet.getInt(5)))
                        .setQuantity(resultSet.getInt(6))
                        .build();
            }
            return null;

        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }


    }

    private User findUserById(int userId) throws ServiceException {
        UserService userService = new UserServiceImpl();
        return userService.findUserById(userId);
    }

    private MealTime findMealTimeById(int mealTimeId) throws ServiceException {
        MealTimeService mealTimeService = new MealTimeServiceImpl();
        return mealTimeService.findMealTimeById(mealTimeId);
    }

    @Override
    public int totalCaloriesByUserIdAndMealDate(int userId, String mealDate) throws DaoException {
        return totalValueByUserIdAndMealDate(userId, mealDate, SQL_COUNT_TOTAL_CALORIES_BY_USER_ID_AND_MEAL_DATE);
    }

    @Override
    public int totalProteinsByUserIdAndMealDate(int userId, String mealDate) throws DaoException {
        return totalValueByUserIdAndMealDate(userId, mealDate, SQL_COUNT_TOTAL_PROTEINS_BY_USER_ID_AND_MEAL_DATE);
    }

    @Override
    public int totalLipidsByUserIdAndMealDate(int userId, String mealDate) throws DaoException {
        return totalValueByUserIdAndMealDate(userId, mealDate, SQL_COUNT_TOTAL_LIPIDS_BY_USER_ID_AND_MEAL_DATE);
    }

    @Override
    public int totalCarbohydratesByUserIdAndMealDate(int userId, String mealDate) throws DaoException {
        return totalValueByUserIdAndMealDate(userId, mealDate, SQL_COUNT_TOTAL_CARBOHYDRATES_BY_USER_ID_AND_MEAL_DATE);
    }

    @Override
    public void deleteMealByUserId(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_MEAL_BY_USER_ID);
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    private Product findProductById(int productId) throws ServiceException {
        ProductService productService = new ProductServiceImpl();
        return productService.findByProductId(productId);
    }

    private int totalValueByUserIdAndMealDate(int userId, String mealDate, String sqlQuery) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, mealDate);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
    }
}
