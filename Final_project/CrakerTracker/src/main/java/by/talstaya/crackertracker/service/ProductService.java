package by.talstaya.crackertracker.service;

import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.ServiceException;

import java.util.List;

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

}
