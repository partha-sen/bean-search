package com.pss.beansearch.sequence;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Objects;


public abstract class SequenceGenerator {
    private final EntityManager entityManager;
    private final String SEQUENCE_NAME;
    private final Long INCREMENT_BY;
    private Long LAST_VALUE;
    private Long currentValue;

    public SequenceGenerator(EntityManager entityManager, String sequenceName) {
        this.SEQUENCE_NAME = sequenceName;
        this.entityManager = entityManager;
        Query query = entityManager.createNativeQuery("select increment_by " +
                "from pg_sequences where sequencename = '" + SEQUENCE_NAME + "'");
        List<Long> resultList = query.getResultList();
        this.INCREMENT_BY = resultList.get(0);
    }

    public synchronized Long getValue(){
      if(Objects.isNull(LAST_VALUE) || (currentValue-LAST_VALUE>INCREMENT_BY)){
          Query query = entityManager.createNativeQuery("select nextval('"+ SEQUENCE_NAME + "')");
          List<Long> resultList = query.getResultList();
          LAST_VALUE = resultList.get(0);
          currentValue = resultList.get(0);
      }
      return currentValue++;
    }


}
