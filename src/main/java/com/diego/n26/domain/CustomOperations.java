package com.diego.n26.domain;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class CustomOperations extends Operations<Transaction> {

    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public boolean addOperations(Transaction transaction) {
        if(transaction == null){
            throw new IllegalArgumentException("The parameter transaction cannot be null");
        }

        if(transaction.isElapsedMoreSecondsThan(Parameter.TIME_SECONDS_QUERY_TRANSACTIONS.value(), Instant.now().toEpochMilli())){
            transactions.add(transaction);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<Transaction> getOperations() {
        return  Collections.unmodifiableList(transactions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomOperations that = (CustomOperations) o;
        return Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(transactions);
    }

    @Override
    public String toString() {
        return "CustomOperations{" +
                "transactions=" + transactions +
                '}';
    }
}
