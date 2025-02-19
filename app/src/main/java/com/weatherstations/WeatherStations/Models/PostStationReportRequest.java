package com.WeatherStations.WeatherStations.Models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class PostStationReportRequest {
    public Date local_time;
    public Date reception_time;
    public BigDecimal temperature;
    public BigDecimal humidity;
    public BigDecimal wind_speed;
}
