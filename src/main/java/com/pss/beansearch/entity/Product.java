package com.pss.beansearch.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @EmbeddedId
    ProductId productId;
	String name;
    Double price;
}
