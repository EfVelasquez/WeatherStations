package com.WeatherStations.WeatherStations.Jobs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.text.DecimalFormat;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.WeatherStations.WeatherStations.Repositories.WeatherRepository;
import com.WeatherStations.WeatherStations.Repositories.Models.MissingAlertReport;
import com.WeatherStations.WeatherStations.Repositories.Models.TemperatureAlertReport;

public class TemperatureThresholdJob implements Job{
    

    @Autowired
    private WeatherRepository weatherRepository;


    @Override
    public void execute(JobExecutionContext context){

        String env = System.getenv("ALERT_TEMPERATURE_THRESHOLD");

        if(env!=null){

            List<TemperatureAlertReport> response = weatherRepository.getTemperatureAlert(new BigDecimal(env));
            
            if(response.size()>0){

                LocalDateTime now = LocalDateTime.now();

                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String nowString = now.format(format);

                DecimalFormat temperatureFormat = new DecimalFormat("#.0");
        
                for (TemperatureAlertReport alertReport : response) {
                    System.out.println(nowString+" ALERT. Station "+alertReport.station+" had a 30-second average of "+temperatureFormat.format(alertReport.avg_temperature) +"ºC");
                }
            }
        }
        
    }

}
