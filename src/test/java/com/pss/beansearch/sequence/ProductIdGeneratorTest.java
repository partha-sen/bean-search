package com.pss.beansearch.sequence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ProductIdGeneratorTest {
    private final EntityManager entityManager = mock(EntityManager.class);
    private final Query query = mock(Query.class);

    ProductIdGenerator productIdGenerator3 = getProductIdGeneratorIncrementBy3();
    ProductIdGenerator productIdGenerator4 = getProductIdGeneratorIncrementBy4();



    @Test
    public void testCallingDbSequenceWhenLastValueIsNull(){
        clearInvocations(entityManager);
        productIdGenerator3.getValue();
        verify(entityManager, Mockito.times(1)).createNativeQuery(any());
    }

    @Test
    public void testLocalIncrement(){
        when(query.getResultList()).thenReturn(List.of(1L));
        assertEquals(1, productIdGenerator3.getValue());
        clearInvocations(entityManager);
        assertEquals(2, productIdGenerator3.getValue());
        assertEquals(3, productIdGenerator3.getValue());
        verify(entityManager, Mockito.times(0)).createNativeQuery(any());
    }

    @Test
    public void testCallingDbSequenceWhenExceedLocalLimit(){
        when(query.getResultList()).thenReturn(List.of(4L));
        assertEquals(4, productIdGenerator3.getValue());
        clearInvocations(entityManager);
        assertEquals(5, productIdGenerator3.getValue());
        assertEquals(6, productIdGenerator3.getValue());
        productIdGenerator3.getValue();
        verify(entityManager, Mockito.times(1)).createNativeQuery(any());
    }

    @Test
    public void testSequenceGeneratorConsistency(){
        when(query.getResultList()).thenReturn(List.of(1L));
        assertEquals(1, productIdGenerator3.getValue());

        when(query.getResultList()).thenReturn(List.of(5L));
        assertEquals(5, productIdGenerator4.getValue());

    }



    private ProductIdGenerator getProductIdGeneratorIncrementBy3() {
        when(entityManager.createNativeQuery(any())).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(3L));
        return new ProductIdGenerator(entityManager);
    }

    private ProductIdGenerator getProductIdGeneratorIncrementBy4() {
        when(entityManager.createNativeQuery(any())).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(4L));
        return new ProductIdGenerator(entityManager);
    }

}
