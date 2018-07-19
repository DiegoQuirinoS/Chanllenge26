package com.diego.n26.model;

import java.util.List;

@FunctionalInterface
public interface Calculate<T> {

    long TIME_SECONDS_QUERY_TRANSACTIONS = 60;

    T calculate(Statistics statistics, List<Transaction> transactions) throws IllegalArgumentException;
}
