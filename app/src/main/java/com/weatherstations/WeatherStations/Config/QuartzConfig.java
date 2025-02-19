package com.WeatherStations.WeatherStations.Config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.WeatherStations.WeatherStations.Jobs.MissingAlertJob;
import com.WeatherStations.WeatherStations.Jobs.TemperatureThresholdJob;

import org.quartz.*;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail MissingAlertJobDetail() {
        return JobBuilder.newJob(MissingAlertJob.class)
                .withIdentity("MissingAlertJob")
                .storeDurably()
                .build();
    }

    @Bean
    public JobDetail TemperatureJobDetail() {
        return JobBuilder.newJob(TemperatureThresholdJob.class)
                .withIdentity("TemperatureThresholdJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger AlertTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(30)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(MissingAlertJobDetail())
                .withIdentity("MissingAlertJobDetail")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger TemperatureTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(TemperatureJobDetail())
                .withIdentity("TemperatureJobDetail")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
