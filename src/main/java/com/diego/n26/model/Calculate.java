package com.diego.n26.model;

import java.util.List;

@FunctionalInterface
public interface Calculate<T> {

    T calculate(Statistics statistics, List<Transaction> transactions) throws IllegalArgumentException;
}
