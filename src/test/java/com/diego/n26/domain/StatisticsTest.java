package com.diego.n26.domain;

import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    private static final long CONST_TIME = 60000L;

    private static final List<Transaction> TRANSACTIONS_LIST = Arrays.asList(
            new Transaction(10.0, Instant.now().toEpochMilli()),
            new Transaction(10.0, Instant.now().toEpochMilli()),
            new Transaction(10.0, Instant.now().toEpochMilli()));

    private static final List<Transaction> TRANSACTIONS_LIST_CONST_TIME = Arrays.asList(
            new Transaction(10.0, CONST_TIME),
            new Transaction(10.0, CONST_TIME),
            new Transaction(10.0, CONST_TIME));

    private final Calculate calculate = new TransactionCalculate();

    @Test
    public void shouldGetStatitistcsWithAllTransactionsTest(){

        long epochMilli = Instant.now().toEpochMilli();

        final Statistics statistics = new Statistics(epochMilli, TRANSACTIONS_LIST);

        final Statistics expected  = new Statistics(epochMilli, TRANSACTIONS_LIST);
        expected.setMax(10.0);
        expected.setMin(10.0);
        expected.setAvg(10.0);
        expected.setCount(3.0);
        expected.setSum(30.0);

        Statistics statiticsFrom = statistics.getStatiticsFrom(calculate);

        assertEquals(expected, statiticsFrom);
    }

    @Test
    public void shouldGetDefaultStatitistcsTest(){

        long epochMilli = 130000l;

        final Statistics statistics = new Statistics(epochMilli, TRANSACTIONS_LIST_CONST_TIME);

        final Statistics expected  = new Statistics(epochMilli, TRANSACTIONS_LIST_CONST_TIME);

        Statistics statiticsFrom = statistics.getStatiticsFrom(calculate);

        assertEquals(expected, statiticsFrom);
    }


    @Test
    public void shouldStatitistcsParcialTransactionTest(){

        final List<Transaction> transactions = Arrays.asList(
                new Transaction(10.25, Instant.now().toEpochMilli()),
                new Transaction(20.60, Instant.now().toEpochMilli()),
                new Transaction(33.99, Instant.now().toEpochMilli()),
                new Transaction(120.0, 1478192204000l),
                new Transaction(52.31, 1359195287211l));

        final long epochMilli = Instant.now().toEpochMilli();

        final Statistics statistics = new Statistics(epochMilli, transactions);

        final Statistics expected  = new Statistics(epochMilli, transactions);
        expected.setMax(33.99);
        expected.setMin(10.25);
        expected.setAvg(21.61);
        expected.setCount(3.0);
        expected.setSum(64.84);

        Statistics statiticsFrom = statistics.getStatiticsFrom(calculate);

        assertEquals(expected, statiticsFrom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetExceptionWhenCalculateNull(){
        final Statistics statistics = new Statistics(Instant.now().toEpochMilli(), TRANSACTIONS_LIST);
        statistics.getStatiticsFrom(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTransactionsListNull(){
        final Statistics statistics = new Statistics(Instant.now().toEpochMilli(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTimeStampZero(){
        final Statistics statistics = new Statistics(0, TRANSACTIONS_LIST);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTimeStampNegative(){
        final Statistics statistics = new Statistics(-1, TRANSACTIONS_LIST);
    }

}
