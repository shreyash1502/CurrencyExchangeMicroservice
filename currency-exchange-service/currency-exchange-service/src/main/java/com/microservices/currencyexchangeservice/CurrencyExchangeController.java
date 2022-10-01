package com.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyExchangeRepository repo;
    @GetMapping("/currency-exchange/from/{from_cur}/to/{to_cur}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from_cur,
            @PathVariable String to_cur){
            logger.info("Retrive exchange value called with {} to {}",from_cur,to_cur);
//        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from_cur, to_cur , BigDecimal.valueOf(50));
        CurrencyExchange currencyExchange=repo.findByFrom_curAndTo_cur(from_cur,to_cur);
        if(currencyExchange==null){
            throw new RuntimeException("unable to find");
        }
        String port=environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }

}
