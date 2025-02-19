package com.WeatherStations.WeatherStations.Handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.WeatherStations.WeatherStations.Models.GetAggregatedReportResponse;
import com.WeatherStations.WeatherStations.Models.AggregatedDataResponse;
import com.WeatherStations.WeatherStations.Models.PostStationReportRequest;
import com.WeatherStations.WeatherStations.Models.GetStationReportResponse;
import com.WeatherStations.WeatherStations.Repositories.WeatherRepository;
import com.WeatherStations.WeatherStations.Repositories.Models.AggregatedReport;
import com.WeatherStations.WeatherStations.Repositories.Models.StationReport;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class WeatherHandler {


    @Autowired
    private WeatherRepository weatherRepository;

    
    public ResponseEntity<GetStationReportResponse> GetLastReport(String stationCode){
        
        StationReport response = weatherRepository.getLastReport(stationCode.toUpperCase());

        if(response != null){
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(
                new GetStationReportResponse(response.station, response.local_time, 
                response.reception_time, response.temperature, response.humidity, response.wind_speed));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> PostReport(String stationCode,PostStationReportRequest reportData){

        weatherRepository.insertReport(stationCode.toUpperCase(),
        reportData.local_time != null ? new Timestamp(reportData.local_time.getTime()) : null,
        reportData.reception_time != null ? new Timestamp(reportData.reception_time.getTime()) : null,
        reportData.temperature,reportData.humidity,reportData.wind_speed);
        
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<GetAggregatedReportResponse> GetAggregatedReport(String stationCode,Timestamp startDate, Timestamp endDate){
        
        AggregatedReport response = weatherRepository.getAggregatedReport(stationCode.toUpperCase(),startDate,endDate);

        if(response != null){
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(
            new GetAggregatedReportResponse(
                response.station,
                new AggregatedDataResponse(response.avg_temperature,response.min_temperature,response.max_temperature),
                new AggregatedDataResponse(response.avg_wind_speed,response.min_wind_speed,response.max_wind_speed),
                new AggregatedDataResponse(response.avg_humidity,response.min_humidity,response.max_humidity)
                ));
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }


}
