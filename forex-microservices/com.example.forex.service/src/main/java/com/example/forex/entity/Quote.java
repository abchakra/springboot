package com.example.forex.entity;

import java.util.Map;
import com.fasterxml.jackson.annotation.*;

public class Quote {
    private Rates rates;
    private long code;

    @JsonProperty("rates")
    public Rates getRates() { return rates; }
    @JsonProperty("rates")
    public void setRates(Rates value) { this.rates = value; }

    @JsonProperty("code")
    public long getCode() { return code; }
    @JsonProperty("code")
    public void setCode(long value) { this.code = value; }
}

// Rates.java

