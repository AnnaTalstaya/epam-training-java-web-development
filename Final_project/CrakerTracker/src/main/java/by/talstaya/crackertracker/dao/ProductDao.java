package by.talstaya.crackertracker.dao;

import by.talstaya.crackertracker.entity.Product;
import by.talstaya.crackertracker.exception.DaoException;

import java.util.List;

public abstract class ProductDao extends BasicDao<Product> {

    public abstract List<Product> takeAllProducts() throws DaoException;

    public abstract List<Product> findByNameOrWordInName(String nameOrWordInName) throws DaoException;

    public abstract List<Product> findProductsByFilter(String nameOrWordInName,
                                                       int minCalories, int maxCalories,
                                                       int minProteins, int maxProteins,
                                                       int minLipids, int maxLipids,
                                                       int minCarbohydrates, int maxCarbohydrates) throws DaoException;

    public abstract List<Product> findProductsByFilterWithoutSearchParam(
                                                       int minCalories, int maxCalories,
                                                       int minProteins, int maxProteins,
                                                       int minLipids, int maxLipids,
                                                       int minCarbohydrates, int maxCarbohydrates) throws DaoException;



//    public abstract List<Product> findByCalories(int minCalories, int maxCalories) throws DaoException;
//
//    public abstract List<Product> findByProteins(int minProteins, int maxProteins);
//
//    public abstract List<Product> findByLipids(int minLipids, int maxLipids);
//
//    public abstract List<Product> findByCarbohydrates(int minCarbohydrates, int maxCarbohydrate);
//
//    public abstract List<Product> sortByName(String name);
//
//    public abstract List<Product> sortByCalories(int calories);
//
//    public abstract List<Product> sortByProteins(int proteins);
//
//    public abstract List<Product> sortByLipids(int lipids);
//
//    public abstract List<Product> sortByCarbohydrates(int carbohydrates);

    public abstract int findMinCalories() throws DaoException;

    public abstract int findMinProteins() throws DaoException;

    public abstract int findMinLipids() throws DaoException;

    public abstract int findMinCarbohydrates() throws DaoException;

    public abstract int findMaxCalories() throws DaoException;

    public abstract int findMaxProteins() throws DaoException;

    public abstract int findMaxLipids() throws DaoException;

    public abstract int findMaxCarbohydrates() throws DaoException;

}
