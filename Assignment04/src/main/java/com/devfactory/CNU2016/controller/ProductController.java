package com.devfactory.CNU2016.controller;

/**
 * Created by siddhanthgupta on 7/7/16.
 */

import com.devfactory.CNU2016.model.Product;
import com.devfactory.CNU2016.repository.ProductCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class used for creating a RESTful API to expose
 * the Product table
 */

@RestController
public class ProductController {

    @Autowired
    ProductCrud productCrud;

    @Autowired
    ControllerUtils controllerUtils;

    /**
     * GET /api/products/
     * Gets all available products
     *
     * @return  List of all available products
     */
    @RequestMapping(value="/products", method= RequestMethod.GET)
    public List<Product> retrieveProducts() {
        return productCrud.findByIsAvailable(true);
    }

    /**
     * POST /api/products/
     * Creates a new product using the data in the request body
     *
     * @param product The Product instance created using the data in the
     *                POST request body
     * @return  a ResponseEntity containing the Product instance in the body
     *          or an status error code in case of error
     */
    @RequestMapping(value="/products", method= RequestMethod.POST)
    public ResponseEntity addProduct(@RequestBody Product product) {
        Map<String, String> errorMap = new HashMap<>();
        if(product.getId() != null) {
            errorMap.put("detail","Product ID should not be passed. Invalid Request");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMap);
        }

        if(controllerUtils.isProductCodeNull(product, errorMap)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMap);
        }
        productCrud.save(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    /**
     * GET /api/products/{id}
     * Get the product having idProduct = given ID in the request URI
     *
     * @param id The ID parameter passed in the request-URI
     * @return  a ResponseEntity containing the Product instance in the body
     *          or an status error code (and detailed error message) in case of
     *          error
     */
    @RequestMapping(value="/products/{id}", method= RequestMethod.GET)
    public ResponseEntity getProduct(@PathVariable Integer id) {
        Map<String, String> errorMap = new HashMap<>();
        if(controllerUtils.isIdNull(id, errorMap)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMap);
        }
        Product product = productCrud.findByIdAndIsAvailable(id, true);
        if(!controllerUtils.isProductExisting(product, errorMap)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorMap);
        }
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(product);
    }

    /**
     * PUT /app/products/{id}
     * Put the passed parameters into the product having the existing
     * ID
     *
     * All attributes in the original product are overwritten by the attributes
     * of the product passed in the request body
     *
     * @param id        The ID parameter passed in the request-URI
     * @param product   The Product instance created using the data in the
     *                  request body
     * @@return a ResponseEntity containing the Product instance in the body
     *          or an status error code (and detailed error message) in case of
     *          error
     */
    @RequestMapping(value="/products/{id}", method= RequestMethod.PUT)
    public ResponseEntity putProduct(@PathVariable Integer id, @RequestBody Product product) {
        Map<String, String> errorMap = new HashMap<>();
        if(controllerUtils.isIdNull(id, errorMap)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMap);
        }
        if(controllerUtils.isProductCodeNull(product, errorMap)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMap);
        }
        Product productOriginal = productCrud.findByIdAndIsAvailable(id, true);;
        if(!controllerUtils.isProductExisting(productOriginal, errorMap)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorMap);
        }
        product.setId(id);
        Product updatedProduct = productCrud.save(product);
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedProduct);
    }

    /**
     * PATCH /app/products/{id}
     * Patch the passed parameters into the product having the existing
     * ID
     *
     * Attributes having null values in the request body do not overwrite
     * the original product in the database.
     *
     * Attributes having non-null values in the request body overwrite the
     * attributes in the original product.
     *
     * @param id        The ID parameter passed in the request-URI
     * @param product   The Product instance created using the data in the
     *                  request body
     * @@return a ResponseEntity containing the Product instance in the body
     *          or an status error code (and detailed error message) in case of
     *          error
     */
    @RequestMapping(value="/products/{id}", method= RequestMethod.PATCH)
    public ResponseEntity patchProduct(@PathVariable Integer id, @RequestBody Product product) {
        Map<String, String> errorMap = new HashMap<>();
        if(controllerUtils.isIdNull(id, errorMap)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMap);
        }
        Product productOriginal = productCrud.findByIdAndIsAvailable(id, true);;
        if(!controllerUtils.isProductExisting(productOriginal, errorMap)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorMap);
        }
        Product mergedProduct = productOriginal.mergeProduct(product);
        mergedProduct.setId(id);
        Product updatedProduct = productCrud.save(mergedProduct);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedProduct);
    }

    /**
     * DELETE /api/products/{id}
     * Delete an Product from the database
     *
     * Instead of deleting an item from the database, we simply maintain a flag
     * called isAvailable which is true if the product has not been deleted, and
     * false if the product is deleted.
     *
     * On deletion, the flag is marked false, and the Product object is passed to
     * the database
     *
     * @param id    The ID parameter passed in the request-URI
     * @return  The status code 204 (no content) if the deletion was successful
     *          404 otherwise
     */
    @RequestMapping(value="/products/{id}", method= RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        Product product = productCrud.findByIdAndIsAvailable(id, true);
        Map<String, String> errorMap = new HashMap<>();
        if(!controllerUtils.isProductExisting(product, errorMap)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorMap);
        }
        product.setIsAvailable(false);
        productCrud.save(product);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

}
