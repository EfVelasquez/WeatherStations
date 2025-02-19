package com.WeatherStations.WeatherStations.Repositories.Models;

import java.math.BigDecimal;


public class MissingAlertReport {
    public String station;

    public MissingAlertReport(String p_station){
        this.station=p_station;
    }
}
