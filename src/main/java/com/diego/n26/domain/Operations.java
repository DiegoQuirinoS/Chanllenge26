package com.diego.n26.domain;

import java.util.List;

public abstract class Operations<T> {

    public abstract boolean  addOperations(T t) throws IllegalArgumentException;

    public abstract List<T> getOperations();
}
