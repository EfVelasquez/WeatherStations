package com.WeatherStations.WeatherStations.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.WeatherStations.WeatherStations.Models.AggregatedReport;
import com.WeatherStations.WeatherStations.Models.StationReport;

import java.util.Date;

import javax.print.attribute.standard.Media;

@RestController
public class WeatherController {

    @GetMapping("/weather/{stationCode}/last")
	public ResponseEntity<StationReport> GetLastWeatherReport(@PathVariable String stationCode){
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new StationReport());
	}

    @PostMapping("/weather/{stationCode}")
	public ResponseEntity<Void> PostReport(@PathVariable String stationCode,@RequestBody StationReport request){
		return ResponseEntity.ok().build();
	}

    @GetMapping("/weather/{stationCode}/range")
	public ResponseEntity<AggregatedReport> GetAggregatedData(@PathVariable String stationCode,
                    @RequestParam(name = "startDate", required = false) Date startDate,
                    @RequestParam(name = "endDate", required = false) Date endDate
                    ) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new AggregatedReport());
	}

}
