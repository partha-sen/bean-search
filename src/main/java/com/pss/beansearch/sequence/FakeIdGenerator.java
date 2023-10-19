package com.pss.beansearch.sequence;


import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class FakeIdGenerator extends SequenceGenerator{

    public FakeIdGenerator(EntityManager entityManager) {
        super(entityManager, "Fake_sequenceid_generator");
    }

}
