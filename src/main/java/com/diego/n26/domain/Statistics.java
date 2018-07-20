package com.diego.n26.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Statistics {

    @JsonCreator
    public Statistics(@JsonProperty("timeStamp")long timeStamp, @JsonProperty("transactions") List<Transaction> transactions){
        if(timeStamp <= 0){
            throw new IllegalArgumentException("The parameter timeStamp cannot be zero or negative!");
        }

        if(transactions == null) {
            throw new IllegalArgumentException("The parameter transactions cannot be null!");
        }

        this.timeStamp = timeStamp;
        this.transactions = transactions;
    }

    private long timeStamp;
    private List<Transaction> transactions;
    private double sum = 0.0;
    private double avg = 0.0;
    private double max = 0.0;
    private double min = Double.MAX_VALUE;
    private double count = 0.0;

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setAvg(double avg) {
        this.avg = Math.floor(avg);
    }

    public void setMax(double max) {
        if(max > this.max) {
            this.max = max;
        }
    }

    public void setMin(double min) {
        if(min < this.min) {
            this.min = min;
        }
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getCount() {
        return count;
    }

    @JsonIgnore
    public Statistics getStatiticsFrom(Calculate calculate) throws IllegalArgumentException{

        if(calculate == null) {
            throw new IllegalArgumentException("The parameter cannot be null");
        }

        List<Transaction> validateTransactions = this.transactions.stream()
                .filter(transaction -> transaction.isElapsedMoreSecondsThan(Parameter.TIME_SECONDS_QUERY_TRANSACTIONS.value(), this.timeStamp))
                .collect(Collectors.toList());

        return (Statistics) calculate.calculate(this, validateTransactions);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(sum, that.sum) &&
                Objects.equals(avg, that.avg) &&
                Objects.equals(max, that.max) &&
                Objects.equals(min, that.min) &&
                Objects.equals(count, that.count);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(sum, avg, max, min, count);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "Statistics{" +
                "timeStamp=" + timeStamp +
                ", transactions=" + transactions +
                ", sum=" + sum +
                ", avg=" + avg +
                ", max=" + max +
                ", min=" + min +
                ", count=" + count +
                '}';
    }
}
