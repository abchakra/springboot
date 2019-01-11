package com.example.forex.entity;

import java.util.Map;
import com.fasterxml.jackson.annotation.*;

public class Eurgbp {
    private double rate;
    private long timestamp;

    @JsonProperty("rate")
    public double getRate() { return rate; }
    @JsonProperty("rate")
    public void setRate(double value) { this.rate = value; }

    @JsonProperty("timestamp")
    public long getTimestamp() { return timestamp; }
    @JsonProperty("timestamp")
    public void setTimestamp(long value) { this.timestamp = value; }
}