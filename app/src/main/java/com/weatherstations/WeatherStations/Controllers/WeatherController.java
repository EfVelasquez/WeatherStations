package com.WeatherStations.WeatherStations.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.WeatherStations.WeatherStations.Handlers.WeatherHandler;

import com.WeatherStations.WeatherStations.Models.GetAggregatedReportResponse;
import com.WeatherStations.WeatherStations.Models.GetStationReportResponse;
import com.WeatherStations.WeatherStations.Models.PostStationReportRequest;
import com.WeatherStations.WeatherStations.Repositories.Models.StationReport;

import java.sql.Timestamp;
import java.util.Date;

@RestController
public class WeatherController {

    @Autowired
    private WeatherHandler weatherHandler;


    @GetMapping("/weather/{stationCode}/last") //Get last station report Endpoint
	public ResponseEntity<GetStationReportResponse> GetLastWeatherReport(@PathVariable String stationCode){

        ResponseEntity<GetStationReportResponse> response = weatherHandler.GetLastReport(stationCode);

		return response;
	}

    @PostMapping("/weather/{stationCode}") //Post new station report Endpoint
	public ResponseEntity<Void> PostReport(@PathVariable String stationCode,@RequestBody PostStationReportRequest request){

        ResponseEntity<Void> response = weatherHandler.PostReport(stationCode,request);

		return response;
	}



    @GetMapping("/weather/{stationCode}/range")  //Get aggregated station report
	public ResponseEntity<GetAggregatedReportResponse> GetAggregatedData(@PathVariable String stationCode,
                    @RequestParam(name = "startDate", required = false) Date startDate,
                    @RequestParam(name = "endDate", required = false) Date endDate
                    ) {

        ResponseEntity<GetAggregatedReportResponse> response = weatherHandler.GetAggregatedReport(stationCode, 
            startDate != null ? new Timestamp(startDate.getTime()) : null,
            endDate != null ? new Timestamp(endDate.getTime()) : null);

		return response;
	}

}
