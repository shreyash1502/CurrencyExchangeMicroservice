package com.microservices.currencyexchangeservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="currencyexchange")
public class CurrencyExchange {

    @Id
    private Long id;

    private String from_cur;

    private String to_cur;

    private BigDecimal conversionMultiple;

    private String environment;
    public CurrencyExchange(){

    }
    public CurrencyExchange(Long id, String from_cur, String to_cur, BigDecimal conversionMultiple) {
        this.id = id;
        this.from_cur = from_cur;
        this.to_cur = to_cur;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom_cur() {
        return from_cur;
    }

    public void setFrom_cur(String from_cur) {
        this.from_cur = from_cur;
    }

    public String getTo_cur() {
        return to_cur;
    }

    public void setTo_cur(String to_cur) {
        this.to_cur = to_cur;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }


}
