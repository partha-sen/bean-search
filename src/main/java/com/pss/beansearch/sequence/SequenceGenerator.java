package com.pss.beansearch.sequence;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Objects;


public abstract class SequenceGenerator {
    private final EntityManager entityManager;
    private final String SEQUENCE_NAME;
    private final Long INCREMENT_BY;
    private Long lastValue;
    private Long currentValue;

    private Long retrieveValue(String sqlQuery){
        Query query = entityManager.createNativeQuery(sqlQuery);
        List<Long> resultList = query.getResultList();
        return resultList.get(0);
    }

    public SequenceGenerator(EntityManager entityManager, String sequenceName) {
        this.SEQUENCE_NAME = sequenceName;
        this.entityManager = entityManager;
        String sqlQuery = "select increment_by " +
                "from pg_sequences where sequencename = '" +
                SEQUENCE_NAME + "'";
        this.INCREMENT_BY = retrieveValue(sqlQuery);
    }

    public synchronized Long getValue(){
      if(Objects.isNull(lastValue) || (currentValue- lastValue >=INCREMENT_BY)){
          String sqlQuery = "select nextval('" +
                  SEQUENCE_NAME + "')";
          Long dbValue = retrieveValue(sqlQuery);
          lastValue = dbValue;
          currentValue = dbValue;
      }
      return currentValue++;
    }


}
