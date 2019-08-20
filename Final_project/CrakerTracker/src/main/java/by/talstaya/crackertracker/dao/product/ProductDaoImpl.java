package by.talstaya.crackertracker.dao.product;

import by.talstaya.crackertracker.connection.ConnectionPool;
import by.talstaya.crackertracker.dao.ProductDao;
import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of ProductDao
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ProductDaoImpl implements ProductDao {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static final String SQL_FIND_PRODUCTS = "SELECT id, name, image_url, description, calories, proteins, lipids, carbohydrates FROM products";
    private static final String SQL_FIND_BY_NAME_OR_WORD_IN_NAME_WITH_LIMIT = "SELECT id, name, image_url, description, calories, proteins, lipids, carbohydrates" +
            " FROM products" +
            " WHERE name LIKE ?" +
            " OR name LIKE ?" +
            " OR name LIKE ?" +
            " OR name LIKE ?" +
            " LIMIT ?,?";
    private static final String SQL_FIND_BY_NAME_OR_WORD_IN_NAME = "SELECT id, name, image_url, description, calories, proteins, lipids, carbohydrates" +
            " FROM products" +
            " WHERE name LIKE ?" +
            " OR name LIKE ?" +
            " OR name LIKE ?" +
            " OR name LIKE ?";

    private static final String SQL_FIND_BY_FILTER_WITH_LIMIT = "SELECT id, name, image_url, description, calories, proteins, lipids, carbohydrates" +
            " FROM products" +
            " WHERE (name LIKE ?" +
            " OR name LIKE ?" +
            " OR name LIKE ?" +
            " OR name LIKE ?)" +
            " AND (calories BETWEEN ? AND ?)" +
            " AND (proteins BETWEEN ? AND ?)" +
            " AND (lipids BETWEEN ? AND ?)" +
            " AND (carbohydrates BETWEEN ? AND ?)" +
            " LIMIT ?,?";

    private static final String SQL_FIND_BY_FILTER = "SELECT id, name, image_url, description, calories, proteins, lipids, carbohydrates" +
            " FROM products" +
            " WHERE (name LIKE ?" +
            " OR name LIKE ?" +
            " OR name LIKE ?" +
            " OR name LIKE ?)" +
            " AND (calories BETWEEN ? AND ?)" +
            " AND (proteins BETWEEN ? AND ?)" +
            " AND (lipids BETWEEN ? AND ?)" +
            " AND (carbohydrates BETWEEN ? AND ?)";

    private static final String SQL_FIND_BY_FILTER_WITHOUT_SEARCH_PARAM_WITH_LIMIT = "SELECT id, name, image_url, description, calories, proteins, lipids, carbohydrates" +
            " FROM products" +
            " WHERE (calories BETWEEN ? AND ?)" +
            " AND (proteins BETWEEN ? AND ?)" +
            " AND (lipids BETWEEN ? AND ?)" +
            " AND (carbohydrates BETWEEN ? AND ?)" +
            " LIMIT ?,?";

    private static final String SQL_FIND_BY_FILTER_WITHOUT_SEARCH_PARAM = "SELECT id, name, image_url, description, calories, proteins, lipids, carbohydrates" +
            " FROM products" +
            " WHERE (calories BETWEEN ? AND ?)" +
            " AND (proteins BETWEEN ? AND ?)" +
            " AND (lipids BETWEEN ? AND ?)" +
            " AND (carbohydrates BETWEEN ? AND ?)";


    private static final String SQL_DELETE_PRODUCT = "DELETE FROM products WHERE id=?";
    private static final String SQL_UPDATE_PRODUCT = "UPDATE products SET name=?, image_url=?, description=?, calories=?, proteins=?, lipids=?, carbohydrates=? WHERE id=?";
    private static final String SQL_FIND_BY_ID = "SELECT id, name, image_url, description, calories, proteins, lipids, carbohydrates FROM products WHERE id=?";
    private static final String SQL_INSERT_PRODUCT = "INSERT INTO products (name, image_url, description, calories, proteins, lipids, carbohydrates) VALUES (?,?,?,?,?,?,?)";

    private static final String SQL_FIND_MIN_CALORIES = "SELECT MIN(calories) FROM products";
    private static final String SQL_FIND_MIN_PROTEINS = "SELECT MIN(proteins) FROM products";
    private static final String SQL_FIND_MIN_LIPIDS = "SELECT MIN(lipids) FROM products";
    private static final String SQL_FIND_MIN_CARBOHYDRATES = "SELECT MIN(carbohydrates) FROM products";

    private static final String SQL_FIND_MAX_CALORIES = "SELECT MAX(calories) FROM products";
    private static final String SQL_FIND_MAX_PROTEINS = "SELECT MAX(proteins) FROM products";
    private static final String SQL_FIND_MAX_LIPIDS = "SELECT MAX(lipids) FROM products";
    private static final String SQL_FIND_MAX_CARBOHYDRATES = "SELECT MAX(carbohydrates) FROM products";

    private static final String SQL_FIND_BY_LIMIT = "SELECT id, name, image_url, description, calories, proteins, lipids, carbohydrates" +
            " FROM products LIMIT ?, ?";

    @Override
    public List<Product> takeAllProducts() throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Product> products = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_PRODUCTS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product.Builder()
                        .setProductId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setCalories(resultSet.getInt(5))
                        .setProteins(resultSet.getInt(6))
                        .setLipids(resultSet.getInt(7))
                        .setCarbohydrates(resultSet.getInt(8))
                        .build()
                );
            }
            return products;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }

    }

    @Override
    public List<Product> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Product> products = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME_OR_WORD_IN_NAME_WITH_LIMIT);
            preparedStatement.setString(1, nameOrWordInName + " %");
            preparedStatement.setString(2, "% " + nameOrWordInName + " %");
            preparedStatement.setString(3, "% " + nameOrWordInName);
            preparedStatement.setString(4, nameOrWordInName);
            preparedStatement.setInt(5, startIndex);
            preparedStatement.setInt(6, endIndex);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product.Builder()
                        .setProductId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setCalories(resultSet.getInt(5))
                        .setProteins(resultSet.getInt(6))
                        .setLipids(resultSet.getInt(7))
                        .setCarbohydrates(resultSet.getInt(8))
                        .build()
                );
            }
            return products;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Product> findByNameOrWordInName(String nameOrWordInName) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Product> products = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME_OR_WORD_IN_NAME);
            preparedStatement.setString(1, nameOrWordInName + " %");
            preparedStatement.setString(2, "% " + nameOrWordInName + " %");
            preparedStatement.setString(3, "% " + nameOrWordInName);
            preparedStatement.setString(4, nameOrWordInName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product.Builder()
                        .setProductId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setCalories(resultSet.getInt(5))
                        .setProteins(resultSet.getInt(6))
                        .setLipids(resultSet.getInt(7))
                        .setCarbohydrates(resultSet.getInt(8))
                        .build()
                );
            }
            return products;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Product> findProductsByFilterWithLimit(String nameOrWordInName,
                                                       int minCalories, int maxCalories,
                                                       int minProteins, int maxProteins,
                                                       int minLipids, int maxLipids,
                                                       int minCarbohydrates, int maxCarbohydrates,
                                                       int startIndex, int endIndex) throws DaoException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Product> products = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_FILTER_WITH_LIMIT);
            preparedStatement.setString(1, nameOrWordInName + " %");
            preparedStatement.setString(2, "% " + nameOrWordInName + " %");
            preparedStatement.setString(3, "% " + nameOrWordInName);
            preparedStatement.setString(4, nameOrWordInName);
            preparedStatement.setInt(5, minCalories);
            preparedStatement.setInt(6, maxCalories);
            preparedStatement.setInt(7, minProteins);
            preparedStatement.setInt(8, maxProteins);
            preparedStatement.setInt(9, minLipids);
            preparedStatement.setInt(10, maxLipids);
            preparedStatement.setInt(11, minCarbohydrates);
            preparedStatement.setInt(12, maxCarbohydrates);
            preparedStatement.setInt(13, startIndex);
            preparedStatement.setInt(14, endIndex);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product.Builder()
                        .setProductId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setCalories(resultSet.getInt(5))
                        .setProteins(resultSet.getInt(6))
                        .setLipids(resultSet.getInt(7))
                        .setCarbohydrates(resultSet.getInt(8))
                        .build()
                );
            }
            return products;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Product> findProductsByFilter(String nameOrWordInName, int minCalories, int maxCalories, int minProteins, int maxProteins, int minLipids, int maxLipids, int minCarbohydrates, int maxCarbohydrates) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Product> products = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_FILTER);
            preparedStatement.setString(1, nameOrWordInName + " %");
            preparedStatement.setString(2, "% " + nameOrWordInName + " %");
            preparedStatement.setString(3, "% " + nameOrWordInName);
            preparedStatement.setString(4, nameOrWordInName);
            preparedStatement.setInt(5, minCalories);
            preparedStatement.setInt(6, maxCalories);
            preparedStatement.setInt(7, minProteins);
            preparedStatement.setInt(8, maxProteins);
            preparedStatement.setInt(9, minLipids);
            preparedStatement.setInt(10, maxLipids);
            preparedStatement.setInt(11, minCarbohydrates);
            preparedStatement.setInt(12, maxCarbohydrates);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product.Builder()
                        .setProductId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setCalories(resultSet.getInt(5))
                        .setProteins(resultSet.getInt(6))
                        .setLipids(resultSet.getInt(7))
                        .setCarbohydrates(resultSet.getInt(8))
                        .build()
                );
            }
            return products;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Product> findProductsByFilterWithoutSearchParamWithLimit(int minCalories, int maxCalories,
                                                                         int minProteins, int maxProteins,
                                                                         int minLipids, int maxLipids,
                                                                         int minCarbohydrates, int maxCarbohydrates,
                                                                         int startIndex, int endIndex) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Product> products = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_FILTER_WITHOUT_SEARCH_PARAM_WITH_LIMIT);
            preparedStatement.setInt(1, minCalories);
            preparedStatement.setInt(2, maxCalories);
            preparedStatement.setInt(3, minProteins);
            preparedStatement.setInt(4, maxProteins);
            preparedStatement.setInt(5, minLipids);
            preparedStatement.setInt(6, maxLipids);
            preparedStatement.setInt(7, minCarbohydrates);
            preparedStatement.setInt(8, maxCarbohydrates);
            preparedStatement.setInt(9, startIndex);
            preparedStatement.setInt(10, endIndex);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product.Builder()
                        .setProductId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setCalories(resultSet.getInt(5))
                        .setProteins(resultSet.getInt(6))
                        .setLipids(resultSet.getInt(7))
                        .setCarbohydrates(resultSet.getInt(8))
                        .build()
                );
            }
            return products;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Product> findProductsByFilterWithoutSearchParam(int minCalories, int maxCalories, int minProteins, int maxProteins, int minLipids, int maxLipids, int minCarbohydrates, int maxCarbohydrates) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Product> products = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_FILTER_WITHOUT_SEARCH_PARAM);
            preparedStatement.setInt(1, minCalories);
            preparedStatement.setInt(2, maxCalories);
            preparedStatement.setInt(3, minProteins);
            preparedStatement.setInt(4, maxProteins);
            preparedStatement.setInt(5, minLipids);
            preparedStatement.setInt(6, maxLipids);
            preparedStatement.setInt(7, minCarbohydrates);
            preparedStatement.setInt(8, maxCarbohydrates);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product.Builder()
                        .setProductId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setCalories(resultSet.getInt(5))
                        .setProteins(resultSet.getInt(6))
                        .setLipids(resultSet.getInt(7))
                        .setCarbohydrates(resultSet.getInt(8))
                        .build()
                );
            }
            return products;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Product> findProductsByLimit(int startIndex, int endIndex) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Product> products = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_LIMIT);
            preparedStatement.setInt(1, startIndex);
            preparedStatement.setInt(2, endIndex);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product.Builder()
                        .setProductId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setCalories(resultSet.getInt(5))
                        .setProteins(resultSet.getInt(6))
                        .setLipids(resultSet.getInt(7))
                        .setCarbohydrates(resultSet.getInt(8))
                        .build()
                );
            }
            return products;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    private int findMinOrMax(String sqlQuery) throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        List<Product> products = new ArrayList<>();

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException("Impossible to find min or max element");
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public int findMinCalories() throws DaoException {
        return findMinOrMax(SQL_FIND_MIN_CALORIES);
    }

    @Override
    public int findMinProteins() throws DaoException {
        return findMinOrMax(SQL_FIND_MIN_PROTEINS);
    }

    @Override
    public int findMinLipids() throws DaoException {
        return findMinOrMax(SQL_FIND_MIN_LIPIDS);
    }

    @Override
    public int findMinCarbohydrates() throws DaoException {
        return findMinOrMax(SQL_FIND_MIN_CARBOHYDRATES);
    }

    @Override
    public int findMaxCalories() throws DaoException {
        return findMinOrMax(SQL_FIND_MAX_CALORIES);
    }

    @Override
    public int findMaxProteins() throws DaoException {
        return findMinOrMax(SQL_FIND_MAX_PROTEINS);
    }

    @Override
    public int findMaxLipids() throws DaoException {
        return findMinOrMax(SQL_FIND_MAX_LIPIDS);
    }

    @Override
    public int findMaxCarbohydrates() throws DaoException {
        return findMinOrMax(SQL_FIND_MAX_CARBOHYDRATES);
    }

    @Override
    public void insert(Product product) throws DaoException {
        PreparedStatement preparedStatement = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getImageURL());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getCalories());
            preparedStatement.setInt(5, product.getProteins());
            preparedStatement.setInt(6, product.getLipids());
            preparedStatement.setInt(7, product.getCarbohydrates());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(Product product) throws DaoException {
        PreparedStatement preparedStatement = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getImageURL());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getCalories());
            preparedStatement.setInt(5, product.getProteins());
            preparedStatement.setInt(6, product.getLipids());
            preparedStatement.setInt(7, product.getCarbohydrates());
            preparedStatement.setInt(8, product.getProductId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public Product findById(int id) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Product.Builder()
                        .setProductId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setImageURL(resultSet.getString(3))
                        .setDescription(resultSet.getString(4))
                        .setCalories(resultSet.getInt(5))
                        .setProteins(resultSet.getInt(6))
                        .setLipids(resultSet.getInt(7))
                        .setCarbohydrates(resultSet.getInt(8))
                        .build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


}
