package com.WeatherStations.WeatherStations.Repositories.Models;

import java.math.BigDecimal;

public class AggregatedReport {
    public String station;

    public BigDecimal avg_temperature;
    public BigDecimal min_temperature;
    public BigDecimal max_temperature;

    public BigDecimal avg_wind_speed;
    public BigDecimal min_wind_speed;
    public BigDecimal max_wind_speed;

    public BigDecimal avg_humidity;
    public BigDecimal min_humidity;
    public BigDecimal max_humidity;

    public AggregatedReport(String station, BigDecimal avg_temperature, BigDecimal min_temperature, BigDecimal max_temperature,
                BigDecimal avg_wind_speed, BigDecimal min_wind_speed, BigDecimal max_wind_speed,
                BigDecimal avg_humidity, BigDecimal min_humidity, BigDecimal max_humidity) {
            this.station = station;
            this.avg_temperature = avg_temperature;
            this.min_temperature = min_temperature;
            this.max_temperature = max_temperature;
            this.avg_wind_speed = avg_wind_speed;
            this.min_wind_speed = min_wind_speed;
            this.max_wind_speed = max_wind_speed;
            this.avg_humidity = avg_humidity;
            this.min_humidity = min_humidity;
            this.max_humidity = max_humidity;
        }
}
