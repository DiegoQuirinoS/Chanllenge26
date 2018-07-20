package com.diego.n26.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionCalculate implements Calculate<Statistics> {

    @Override
    public Statistics calculate(Statistics statistics, List<Transaction> transactions) {

        if(statistics == null){
            throw new IllegalArgumentException("The parameter statistics cannot be null");
        }

        if(transactions == null){
            throw new IllegalArgumentException("The parameter transactions cannot be null");
        }

        double total = 0;

        for (Transaction transaction : transactions) {
            total = total + transaction.getAmount();
            statistics.setMax(transaction.getAmount());
            statistics.setMin(transaction.getAmount());
        }

        statistics.setSum(total);
        statistics.setCount((double) transactions.size());

        if(!transactions.isEmpty()) {
            statistics.setAvg(total / transactions.size());
        }

        return statistics;
    }
}
