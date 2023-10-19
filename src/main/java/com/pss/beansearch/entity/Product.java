package com.pss.beansearch.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Entity
public class Product implements Serializable, Persistable<ProductId> {
    @EmbeddedId
    ProductId productId;
	String name;
    Double price;

    public ProductId getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }


    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Transient
    private boolean isInsert;


    public void forceInsert(boolean flag) {
        this.isInsert = flag;
    }

    //@Override
    public boolean isNew() {
        return isInsert;
    }

    public ProductId getId(){
      return productId;
    }
}
