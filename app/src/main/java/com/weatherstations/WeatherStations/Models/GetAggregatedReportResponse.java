package com.WeatherStations.WeatherStations.Models;

public class GetAggregatedReportResponse {
    public String station;
    public AggregatedDataResponse temperature;
    public AggregatedDataResponse wind_speed;
    public AggregatedDataResponse humidity;

    public GetAggregatedReportResponse(String p_station,AggregatedDataResponse p_temperature, AggregatedDataResponse p_wind_speed,AggregatedDataResponse p_humidity){
        this.station = p_station;
        this.temperature = p_temperature;
        this.wind_speed = p_wind_speed;
        this.humidity = p_humidity;
    }
}

