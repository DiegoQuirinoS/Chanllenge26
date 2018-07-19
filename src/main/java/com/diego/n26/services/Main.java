package com.diego.n26.services;

import com.diego.n26.model.Calculate;
import com.diego.n26.model.Statistics;
import com.diego.n26.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class Main {

    @Autowired
    Calculate calculate;

    List<Transaction> transactions =  Collections.synchronizedList(new ArrayList<>());

    @PostMapping(name = "/transactions")
    public void transactions(HttpServletResponse response, @RequestBody Transaction transaction){
        transactions.add(transaction);
        if(transaction.isElapsedMoreSecondsThan(Calculate.TIME_SECONDS_QUERY_TRANSACTIONS, Instant.now().toEpochMilli())){
            response.setStatus(HttpServletResponse.SC_CREATED);
        }else{
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    @GetMapping(name = "/statistics")
    public Statistics statistics(){
        Statistics statistics = new Statistics(Instant.now().toEpochMilli(), transactions);
        Statistics statiticsFrom = statistics.getStatiticsFrom(calculate);
        return statiticsFrom;
    }
}
