package com.diego.n26.services;


import com.diego.n26.domain.Calculate;
import com.diego.n26.domain.CustomOperations;
import com.diego.n26.domain.Operations;
import com.diego.n26.domain.TransactionCalculate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    public Calculate getCalculate(){
        return new TransactionCalculate();
    }

    public Operations getOperations() {
        return  new CustomOperations();
    }
}
