package com.diego.n26.model;


import org.junit.Test;

import java.time.Instant;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransactionTest {
    private final Random random = new Random();

    @Test
    public void isElapsedMoreThanSixtySeconds() throws InterruptedException {
        Transaction transaction = new Transaction(random.nextDouble(), Instant.now().toEpochMilli());
        Thread.sleep(70000l);
        assertFalse(transaction.isElapsedMoreSecondsThan(60l, Instant.now().toEpochMilli()));
    }

    @Test
    public void isElapsedEqualSixtySeconds() throws InterruptedException {
        Transaction transaction = new Transaction(random.nextDouble(), Instant.now().toEpochMilli());
        Thread.sleep(60000l);
        assertTrue(transaction.isElapsedMoreSecondsThan(60l, Instant.now().toEpochMilli()));
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithTimeStampZero() throws InterruptedException {
        Transaction transaction = new Transaction(random.nextDouble(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithTimeStampNegative() throws InterruptedException {
        Transaction transaction = new Transaction(random.nextDouble(), -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithAmountNegative() throws InterruptedException {
        Transaction transaction = new Transaction(-1, Instant.now().toEpochMilli());
    }
}
