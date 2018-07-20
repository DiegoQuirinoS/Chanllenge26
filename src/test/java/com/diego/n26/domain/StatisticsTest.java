package com.diego.n26.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    private static final long CONST_TIME = 60000L;
    private static final Operations operations = new CustomOperations();
    private static final Operations operantionsSameTime = new CustomOperations();
    private final Calculate calculate = new TransactionCalculate();

    @BeforeClass
    public static void setUp(){
        operations.addOperations(new Transaction(10.0, Instant.now().toEpochMilli()));
        operations.addOperations(new Transaction(10.0, Instant.now().toEpochMilli()));
        operations.addOperations(new Transaction(10.0, Instant.now().toEpochMilli()));

        operantionsSameTime.addOperations(new Transaction(10.0, CONST_TIME));
        operantionsSameTime.addOperations(new Transaction(10.0, CONST_TIME));
        operantionsSameTime.addOperations(new Transaction(10.0, CONST_TIME));
    }

    @Test
    public void shouldGetStatistcsWithAllTransactionsTest(){

        long epochMilli = Instant.now().toEpochMilli();

        final Statistics statistics = new Statistics(epochMilli, operations);

        final Statistics expected = new Statistics(epochMilli, operations);
        expected.setMax(10.0);
        expected.setMin(10.0);
        expected.setAvg(10.0);
        expected.setCount(3.0);
        expected.setSum(30.0);

        Statistics statiticsFrom = statistics.getStatisticsFrom(calculate);

        assertEquals(expected, statiticsFrom);
    }

    @Test
    public void shouldGetDefaultStatistcsTest(){

        long epochMilli = 130000l;

        final Statistics statistics = new Statistics(epochMilli, operantionsSameTime);

        final Statistics expected  = new Statistics(epochMilli, operantionsSameTime);

        Statistics statiticsFrom = statistics.getStatisticsFrom(calculate);

        assertEquals(expected, statiticsFrom);
    }


    @Test
    public void shouldStatistcsParcialTransactionTest(){
        final Operations operations = new CustomOperations();
        operations.addOperations(new Transaction(10.25, Instant.now().toEpochMilli()));
        operations.addOperations(new Transaction(20.60, Instant.now().toEpochMilli()));
        operations.addOperations(new Transaction(33.99, Instant.now().toEpochMilli()));
        operations.addOperations(new Transaction(120.0, 1478192204000l));
        operations.addOperations(new Transaction(52.31, 1359195287211l));

        final long epochMilli = Instant.now().toEpochMilli();

        final Statistics statistics = new Statistics(epochMilli, operations);

        final Statistics expected  = new Statistics(epochMilli, operations);
        expected.setMax(33.99);
        expected.setMin(10.25);
        expected.setAvg(21.61);
        expected.setCount(3.0);
        expected.setSum(64.84);

        Statistics statiticsFrom = statistics.getStatisticsFrom(calculate);

        assertEquals(expected, statiticsFrom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetExceptionWhenCalculateNull(){
        final Statistics statistics = new Statistics(Instant.now().toEpochMilli(), operations);
        statistics.getStatisticsFrom(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTransactionsListNull(){
        final Statistics statistics = new Statistics(Instant.now().toEpochMilli(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTimeStampZero(){
        final Statistics statistics = new Statistics(0, operations);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTimeStampNegative(){
        final Statistics statistics = new Statistics(-1, operations);
    }

}
