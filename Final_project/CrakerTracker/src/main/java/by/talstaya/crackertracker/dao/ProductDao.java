package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.DaoException;

import java.util.List;

/**
 * This class is a layer for interacting with product database
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface ProductDao extends BasicDao<Product> {

    List<Product> takeAllProducts() throws DaoException;

    List<Product> findByNameOrWordInNameWithLimit(String nameOrWordInName, int startIndex, int endIndex) throws DaoException;

    List<Product> findByNameOrWordInName(String nameOrWordInName) throws DaoException;

    List<Product> findProductsByFilterWithLimit(String nameOrWordInName,
                                                int minCalories, int maxCalories,
                                                int minProteins, int maxProteins,
                                                int minLipids, int maxLipids,
                                                int minCarbohydrates, int maxCarbohydrates,
                                                int startIndex, int endIndex) throws DaoException;

    List<Product> findProductsByFilter(String nameOrWordInName,
                                                int minCalories, int maxCalories,
                                                int minProteins, int maxProteins,
                                                int minLipids, int maxLipids,
                                                int minCarbohydrates, int maxCarbohydrates) throws DaoException;

    List<Product> findProductsByFilterWithoutSearchParamWithLimit(
            int minCalories, int maxCalories,
            int minProteins, int maxProteins,
            int minLipids, int maxLipids,
            int minCarbohydrates, int maxCarbohydrates,
            int startIndex, int endIndex) throws DaoException;

    List<Product> findProductsByFilterWithoutSearchParam(
            int minCalories, int maxCalories,
            int minProteins, int maxProteins,
            int minLipids, int maxLipids,
            int minCarbohydrates, int maxCarbohydrates) throws DaoException;

    List<Product> findProductsByLimit(int startIndex, int endIndex) throws DaoException;

    int findMinCalories() throws DaoException;

    int findMinProteins() throws DaoException;

    int findMinLipids() throws DaoException;

    int findMinCarbohydrates() throws DaoException;

    int findMaxCalories() throws DaoException;

    int findMaxProteins() throws DaoException;

    int findMaxLipids() throws DaoException;

    int findMaxCarbohydrates() throws DaoException;

}
