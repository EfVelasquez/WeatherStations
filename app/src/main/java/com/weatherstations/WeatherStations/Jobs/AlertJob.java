package com.WeatherStations.WeatherStations.Jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class AlertJob implements Job{
    
    @Override
    public void execute(JobExecutionContext context){
        System.out.println("Alert Job.");
    }

}
