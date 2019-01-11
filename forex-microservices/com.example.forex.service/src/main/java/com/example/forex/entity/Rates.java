package com.example.forex.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rates {
    private Eurgbp eurgbp;
    private Eurgbp usdjpy;
    private Eurgbp eurusd;
    


    @JsonProperty("EURUSD")
    public Eurgbp getEurusd() { return eurusd; }
    @JsonProperty("EURUSD")
    public void setEurusd(Eurgbp value) { this.eurusd = value; }
    

    @JsonProperty("EURGBP")
    public Eurgbp getEurgbp() { return eurgbp; }
    @JsonProperty("EURGBP")
    public void setEurgbp(Eurgbp value) { this.eurgbp = value; }

    @JsonProperty("USDJPY")
    public Eurgbp getUsdjpy() { return usdjpy; }
    @JsonProperty("USDJPY")
    public void setUsdjpy(Eurgbp value) { this.usdjpy = value; }
}

// Eurgbp.java

