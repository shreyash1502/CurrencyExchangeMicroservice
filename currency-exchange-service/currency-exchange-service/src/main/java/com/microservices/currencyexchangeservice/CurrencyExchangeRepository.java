package com.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    @Query("Select c from CurrencyExchange c where c.from_cur=?1 and c.to_cur=?2")
    CurrencyExchange findByFrom_curAndTo_cur(String from,String to);

}
