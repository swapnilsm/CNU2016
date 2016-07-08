package com.devfactory.CNU2016.repository;

/**
 * Created by siddhanthgupta on 7/7/16.
 */

import com.devfactory.CNU2016.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Spring Query Repository for the model Product
 */
public interface ProductCrud extends CrudRepository<Product, Integer>{

    /**
     * Returns all available Products
     * @param available Boolean flag: set to true if available objects are to be found
     * @return A List of Products having their available flag = available
     */
    List<Product> findByIsAvailable(Boolean available);

    /**
     * Returns the product having given id, if it's available flag = available
     * @param id        Integer Id field of the Product to be found
     * @param available Boolean flag (set to true if available objects are to be found)
     * @return  The Product which has the given ID and available flag value
     */
    Product findByIdAndIsAvailable(Integer id, Boolean available);
}
