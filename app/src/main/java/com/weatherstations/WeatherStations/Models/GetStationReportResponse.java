package com.WeatherStations.WeatherStations.Models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class GetStationReportResponse {
    public String station;
    public Timestamp local_time;
    public Timestamp reception_time;
    public BigDecimal temperature;
    public BigDecimal humidity;
    public BigDecimal wind_speed;

    public GetStationReportResponse(String station, Timestamp local_time, Timestamp reception_time,
                BigDecimal temperature, BigDecimal humidity, BigDecimal wind_speed) {
        this.station = station;
        this.local_time = local_time;
        this.reception_time = reception_time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
    }

}

