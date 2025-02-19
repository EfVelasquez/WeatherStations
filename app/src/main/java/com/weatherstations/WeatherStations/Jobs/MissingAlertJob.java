package com.WeatherStations.WeatherStations.Jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.WeatherStations.WeatherStations.Repositories.Models.MissingAlertReport;
import com.WeatherStations.WeatherStations.Repositories.Models.TemperatureAlertReport;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.WeatherStations.WeatherStations.Repositories.WeatherRepository;

public class MissingAlertJob implements Job{
    
    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public void execute(JobExecutionContext context){
        List<MissingAlertReport> response = weatherRepository.getMissingAlert();
        
        if(response.size()>0){

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String nowString = now.format(format);
    
            System.out.println(nowString+" ALERT. Missing data from stations:");
            for (MissingAlertReport alertReport : response) {
                System.out.println("-"+alertReport.station);
            }
        }
        

    }

}
