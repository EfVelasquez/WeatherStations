package com.WeatherStations.WeatherStations.Repositories.Models;

import java.math.BigDecimal;


public class AlertReport {
    public String station;
    public BigDecimal avg_temperature;

    public AlertReport(String p_station, BigDecimal p_avg_temperature){
        this.station=p_station;
        this.avg_temperature=p_avg_temperature;
    }
}
