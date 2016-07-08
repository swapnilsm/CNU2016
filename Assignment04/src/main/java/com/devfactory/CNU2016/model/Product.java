package com.devfactory.CNU2016.model;

/**
 * Created by siddhanthgupta on 7/7/16.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Model for the Product table
 */
@Entity(name="Product")
public class Product {

    @Id
    @Column(name = "idProduct")
    @GeneratedValue
    private Integer id;

    private String code;
    private Double buyPrice;

    @Column(name = "description", columnDefinition="TEXT")
    private String description;
    private Integer idCategory;
    private String name;
    private Integer quantity;

    @Column(name = "flag")
    private Boolean isAvailable;

    public Product() {
        isAvailable = true;
        idCategory = null;
        String code = null;
        buyPrice = 0.0;
        description = null;
        name = null;
        quantity = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isFlag() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Merges fields in this with those in otherProduct.
     * If a field in otherProduct is null, then pick field in this
     * else pick field in other
     * @param otherProduct Product to merge (this) with
     * @return The new merged Product instance
     */
    public Product mergeProduct(Product otherProduct) {
        Product mergedProduct = new Product();
        mergedProduct.code = otherProduct.code != null? otherProduct.code: this.code;
        mergedProduct.description = otherProduct.description != null?
                otherProduct.description: this.description;
        return mergedProduct;
    }

}
