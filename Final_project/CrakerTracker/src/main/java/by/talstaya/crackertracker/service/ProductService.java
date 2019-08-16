package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.ServiceException;

import java.util.List;

/**
 * This class is a layer for interacting with ProductDao
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface ProductService {

    List<Product> takeAllProducts() throws ServiceException;

    List<Product> findByNameOrWordInName(String nameOrWordInName) throws ServiceException;

    List<Product> findProductsByFilter(
            String nameOrWordInName,
            int minCalories, int maxCalories,
            int minProteins, int maxProteins,
            int minLipids, int maxLipids,
            int minCarbohydrates, int maxCarbohydrates) throws ServiceException;

    Product findByProductId(int productId) throws ServiceException;

    int findMinCalories() throws ServiceException;

    int findMinProteins() throws ServiceException;

    int findMinLipids() throws ServiceException;

    int findMinCarbohydrates() throws ServiceException;

    int findMaxCalories() throws ServiceException;

    int findMaxProteins() throws ServiceException;

    int findMaxLipids() throws ServiceException;

    int findMaxCarbohydrates() throws ServiceException;

    int checkCalories(int minCalories) throws ServiceException;

    int checkProteins(int minProteins) throws ServiceException;

    int checkLipids(int minLipids) throws ServiceException;

    int checkCarbohydrates(int minCarbohydrates) throws ServiceException;


}
