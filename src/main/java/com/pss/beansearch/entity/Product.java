package com.pss.beansearch.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Product implements Serializable, Persistable<ProductId> {
    @EmbeddedId
    ProductId productId;
	String name;
    Double price;

    @Transient
    boolean isInsert;

    //@Override
    public boolean isNew() {
        return isInsert;
    }

    public ProductId getId(){
      return productId;
    }
}
