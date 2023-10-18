package com.pss.beansearch.sequence;


import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class ProductIdGenerator extends SequenceGenerator{

    public ProductIdGenerator(EntityManager entityManager) {
        super(entityManager, "product_sequenceid_generator");
    }

}
