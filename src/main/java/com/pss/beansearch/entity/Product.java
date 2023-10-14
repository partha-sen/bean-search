package com.pss.beansearch.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@ToString
@IdClass(ProductId.class)
public class Product implements Serializable {
    @Id
    long id;
    @Id
    long version;
	String name;
    Double price;
}
