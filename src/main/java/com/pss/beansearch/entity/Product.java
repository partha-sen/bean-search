package com.pss.beansearch.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@ToString
@IdClass(ProductId.class)
public class Product implements Serializable, Persistable<Long> {
    @Id
    Long id;
    @Id
    Long version;
	String name;
    Double price;

    @Transient
    boolean isInsert;

    //@Override
    public boolean isNew() {
        return isInsert;
    }
}
