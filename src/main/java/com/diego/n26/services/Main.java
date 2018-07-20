package com.diego.n26.services;

import com.diego.n26.domain.Calculate;
import com.diego.n26.domain.Operations;
import com.diego.n26.domain.Statistics;
import com.diego.n26.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@RestController
public class Main {

    @Autowired
    Calculate calculate;

    @Autowired
    Operations operations;

    @PostMapping(name = "/transactions")
    public void transactions(HttpServletResponse response, @RequestBody Transaction transaction){
        boolean isValid = operations.addOperations(transaction);
        if(isValid){
            response.setStatus(HttpServletResponse.SC_CREATED);
        }else{
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    @GetMapping(name = "/statistics")
    public Statistics statistics(){
        Statistics statistics = new Statistics(Instant.now().toEpochMilli(), operations.getOperations());
        Statistics statiticsFrom = statistics.getStatiticsFrom(calculate);
        return statiticsFrom;
    }
}
