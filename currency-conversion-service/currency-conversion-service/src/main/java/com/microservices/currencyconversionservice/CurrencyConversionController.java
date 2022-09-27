package com.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from_cur}/to/{to_cur}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from_cur,
            @PathVariable String to_cur,
            @PathVariable BigDecimal quantity
            ){
        HashMap<String,String> uriVariables=new HashMap<>();
        uriVariables.put("from_cur",from_cur);
        uriVariables.put("to_cur",to_cur);
        ResponseEntity<CurrencyConversion> responseEntity=new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from_cur}/to/{to_cur}",CurrencyConversion.class,uriVariables);
        CurrencyConversion body=responseEntity.getBody();
        return new CurrencyConversion(body.getId(),body.getFrom_cur(), body.getTo_cur(), quantity,body.getConversionMultiple(),quantity.multiply(body.getConversionMultiple()), body.getEnvironment());
    }

    @GetMapping("/currency-conversion-feign/from/{from_cur}/to/{to_cur}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from_cur,
            @PathVariable String to_cur,
            @PathVariable BigDecimal quantity
    ){
       CurrencyConversion body=proxy.retrieveExchangeValue(from_cur,to_cur);
        return new CurrencyConversion(body.getId(),body.getFrom_cur(), body.getTo_cur(), quantity,body.getConversionMultiple(),quantity.multiply(body.getConversionMultiple()), body.getEnvironment());
    }
}
