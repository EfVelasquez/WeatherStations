package com.WeatherStations.WeatherStations.Repositories.Models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="report")
public class StationReport {
    @Id
    Integer id;
    public String station;
    public Timestamp local_time;
    public Timestamp reception_time;
    public BigDecimal temperature;
    public BigDecimal humidity;
    public BigDecimal wind_speed;

}
