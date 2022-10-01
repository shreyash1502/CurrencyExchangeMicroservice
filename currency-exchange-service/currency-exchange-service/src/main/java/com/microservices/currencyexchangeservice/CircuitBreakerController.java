package com.microservices.currencyexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger= LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
//  @Retry(name="default",fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name="default",fallbackMethod = "hardcodedResponse")
    @RateLimiter(name="default")
    public String sampleApi(){
        logger.info("Sample API call Recieved");
        ResponseEntity<String> forEntity= new RestTemplate().getForEntity("http://localhost:7564/my",String.class);
        return forEntity.getBody();
//        return "Sample api";
    }
    public String hardcodedResponse(Exception ex){
        return "Fallback Called";
    }
}


