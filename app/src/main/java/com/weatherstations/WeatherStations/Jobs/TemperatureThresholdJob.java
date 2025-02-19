package com.WeatherStations.WeatherStations.Jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class TemperatureThresholdJob implements Job{
    
    @Override
    public void execute(JobExecutionContext context){
        System.out.println("Temperature Job");
    }

}
