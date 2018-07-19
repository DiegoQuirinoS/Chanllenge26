package com.diego.n26.services;


import com.diego.n26.model.Calculate;
import com.diego.n26.model.TransactionCalculate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    public Calculate getCalculate(){
        return new TransactionCalculate();
    }
}
