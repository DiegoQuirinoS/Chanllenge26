package com.diego.n26.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Transaction {

    @JsonCreator
    public Transaction(@JsonProperty("amount") double amount, @JsonProperty("timestamp")long timestamp){
        if(timestamp <= 0){
            throw new IllegalArgumentException("The timestamp of transaction cannot be zero or negative");
        }

        if(amount < 0){
            throw new IllegalArgumentException("The timestamp of transaction cannot be negative");
        }

        this.amount = amount;
        this.timestamp = timestamp;
    }

    private double amount;
    private long timestamp;

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isElapsedMoreSecondsThan(long seconds, long currentTimeStamp){
        long difference = currentTimeStamp - this.timestamp;
        long differenceInSeconds = this.convertInSeconds(difference);

        if(differenceInSeconds <= seconds){
            return true;
        }

        return false;
    }

    private long convertInSeconds(long milliseconds){
        long seconds = milliseconds / 1000;
        return seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, timestamp);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
