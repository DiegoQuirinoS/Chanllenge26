package com.diego.n26.model;

public enum Parameter {

    TIME_SECONDS_QUERY_TRANSACTIONS(60);

    private long time;

    Parameter(long time){
        this.time = time;
    }

    public long value(){
        return this.time;
    }
}
