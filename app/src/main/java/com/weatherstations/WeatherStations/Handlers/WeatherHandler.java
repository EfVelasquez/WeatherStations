package com.WeatherStations.WeatherStations.Handlers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.WeatherStations.WeatherStations.Models.AggregatedReport;
import com.WeatherStations.WeatherStations.Models.StationReport;
import java.util.Date;

@Service
public class WeatherHandler {
    
    public ResponseEntity<StationReport> GetLastReport(String stationCode){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new StationReport());
    }

    public ResponseEntity<Void> PostReport(String stationCode,StationReport reportData){
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<AggregatedReport> GetAggregatedReport(String stationCode,Date startDate, Date endDate){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new AggregatedReport());
    }


}
