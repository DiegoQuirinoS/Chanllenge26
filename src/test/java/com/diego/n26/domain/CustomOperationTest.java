package com.diego.n26.domain;

import org.junit.Test;

import java.time.Instant;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class CustomOperationTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithTransactionsNull(){
        CustomOperations customOperations = new CustomOperations();
        customOperations.addOperations(null);
    }

    @Test
    public void shouldGetBoolean(){
        CustomOperations customOperations = new CustomOperations();
        boolean current = customOperations.addOperations(new Transaction(new Random().nextDouble(), Instant.now().toEpochMilli()));
        assertTrue(current);
    }

    @Test
    public void shouldGetList(){
        CustomOperations customOperations = new CustomOperations();
        customOperations.addOperations(new Transaction(1, 1));
        List<Transaction> operations = customOperations.getOperations();
        assertTrue(operations != null);
    }
}
