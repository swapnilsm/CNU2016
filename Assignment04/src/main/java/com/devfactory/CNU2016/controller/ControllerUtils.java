package com.devfactory.CNU2016.controller;

import com.devfactory.CNU2016.model.Product;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Contains utilities used by Controller classes
 */
@Component
public class ControllerUtils {


    /**
     * Checks if the product code is null or not
     * If it is, adds detail message to errorMap
     * @param product   Product object to check for null code
     * @param errorMap  A String of key-value pairs which acts as the reponse
     *                  and is appended with the detail message
     */
    public boolean isProductCodeNull(Product product, Map<String, String> errorMap) {
        if(product.getCode() == null) {
            errorMap.put("detail","Code not provided. Invalid Request");
            return true;
        }
        return false;
    }

    /**
     * Checks if the id is null or not
     * If it is, adds detail message to errorMap
     * @param id   Integer object to check for null
     * @param errorMap  A String of key-value pairs which acts as the reponse
     *                  and is appended with the detail message
     */
    public boolean isIdNull(Integer id, Map<String, String> errorMap) {
        if(id == null) {
            errorMap.put("detail","ID Field is null");
            return true;
        }
        return false;
    }

    /**
     * Checks if the product exists or not (checks if product is null or not)
     * If it is, adds detail message to errorMap
     * @param product   Product object to check for null
     * @param errorMap  A String of key-value pairs which acts as the reponse
     *                  and is appended with the detail message
     */
    public boolean isProductExisting(Product product, Map<String, String> errorMap) {
        if(product == null) {
            errorMap.put("detail","Not found.");
            return false;
        }
        return true;
    }
}
