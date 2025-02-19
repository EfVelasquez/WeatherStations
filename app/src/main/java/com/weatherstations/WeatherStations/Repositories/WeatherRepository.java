package com.WeatherStations.WeatherStations.Repositories;
import com.WeatherStations.WeatherStations.Repositories.Models.AggregatedReport;
import com.WeatherStations.WeatherStations.Repositories.Models.AlertReport;
import com.WeatherStations.WeatherStations.Repositories.Models.StationReport;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.type.descriptor.jdbc.DecimalJdbcType;
import org.hibernate.type.descriptor.jdbc.TimestampJdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;


import jakarta.transaction.Transactional;

import org.springframework.data.repository.query.Param;

@Repository
public interface WeatherRepository extends JpaRepository<StationReport, Integer> {


    @Query(value = "SELECT * FROM get_last_report(:p_station) LIMIT 1", nativeQuery = true)
    StationReport getLastReport(@Param("p_station") String p_station);

    @Modifying
    @Transactional
    @Query(value = "CALL insert_report(:p_station,:p_local_time,:p_reception_time,:p_temperature,:p_humidity,:p_wind_speed)", nativeQuery = true)
    void insertReport( 
        @Param("p_station") String p_station,
        @Param("p_local_time")  Timestamp p_local_time,
        @Param("p_reception_time")  Timestamp p_reception_time,
        @Param("p_temperature")  BigDecimal p_temperature,
        @Param("p_humidity")    BigDecimal p_humidity,
        @Param("p_wind_speed")    BigDecimal p_wind_speed);

    @Query(value = "SELECT * FROM get_aggregated_report(:p_station,:p_start_date,:p_end_date) LIMIT 1", nativeQuery = true)
    AggregatedReport getAggregatedReport(
        @Param("p_station") String p_station,
        @Param("p_start_date") Timestamp p_start_date,
        @Param("p_end_date") Timestamp p_end_date);


    @Query(value = "SELECT * FROM  get_alert(:threshold)", nativeQuery = true)
    List<AlertReport> getAlert(@Param("threshold") BigDecimal threshold);

        /*CREATE OR REPLACE FUNCTION get_missingData()
        RETURNS TABLE(
            station VARCHAR) AS $$
        BEGIN */
}
