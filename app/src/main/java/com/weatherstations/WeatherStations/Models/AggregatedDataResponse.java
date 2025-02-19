package com.WeatherStations.WeatherStations.Models;

import java.math.BigDecimal;

public class AggregatedDataResponse {
    public BigDecimal avg;
    public BigDecimal min;
    public BigDecimal max;

    public AggregatedDataResponse(BigDecimal p_avg, BigDecimal p_min, BigDecimal p_max){
        avg = p_avg;
        min = p_min;
        max = p_max;
    }
}

