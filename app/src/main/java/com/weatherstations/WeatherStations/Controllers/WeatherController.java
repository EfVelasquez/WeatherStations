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

import com.WeatherStations.WeatherStations.Models.AggregatedReport;
import com.WeatherStations.WeatherStations.Models.StationReport;

import java.util.Date;

@RestController
public class WeatherController {

    @Autowired
    private WeatherHandler weatherHandler;


    @GetMapping("/weather/{stationCode}/last") //Get last station report Endpoint
	public ResponseEntity<StationReport> GetLastWeatherReport(@PathVariable String stationCode){

        ResponseEntity<StationReport> response = weatherHandler.GetLastReport(stationCode);

		return response;
	}

    @PostMapping("/weather/{stationCode}") //Post new station report Endpoint
	public ResponseEntity<Void> PostReport(@PathVariable String stationCode,@RequestBody StationReport request){

        ResponseEntity<Void> response = weatherHandler.PostReport(stationCode,request);

		return response;
	}



    @GetMapping("/weather/{stationCode}/range")  //Get aggregated station report
	public ResponseEntity<AggregatedReport> GetAggregatedData(@PathVariable String stationCode,
                    @RequestParam(name = "startDate", required = false) Date startDate,
                    @RequestParam(name = "endDate", required = false) Date endDate
                    ) {

        ResponseEntity<AggregatedReport> response = weatherHandler.GetAggregatedReport(stationCode, startDate,endDate);

		return response;
	}

}
