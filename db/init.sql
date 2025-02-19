

CREATE TABLE IF NOT EXISTS report (
    id                      SERIAL PRIMARY KEY,
    station                 VARCHAR(3) NOT NULL,
    local_time               TIMESTAMP NOT NULL,
    reception_time           TIMESTAMP NOT NULL,
    temperature             DECIMAL NOT NULL,
    humidity                DECIMAL NOT NULL,
    wind_speed               DECIMAL NOT NULL
);

CREATE OR REPLACE PROCEDURE insert_report(
    p_station VARCHAR,
    p_local_time TIMESTAMP,
    p_reception_time TIMESTAMP,
    p_temperature DECIMAL,
    p_humidity DECIMAL,
    p_wind_speed DECIMAL
) AS $$
BEGIN
    INSERT INTO report (station, local_time, reception_time, temperature, humidity, wind_speed)
    VALUES (p_station, p_local_time, p_reception_time, p_temperature, p_humidity, p_wind_speed);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_last_report(p_station VARCHAR)
RETURNS TABLE(id INT,
    station VARCHAR,
    local_time TIMESTAMP,
    reception_time TIMESTAMP,
    temperature DECIMAL,
    humidity DECIMAL,
    wind_speed DECIMAL) AS $$
BEGIN
    RETURN QUERY
    SELECT report.id,
        report.station,
        report.local_time,
        report.reception_time,
        report.temperature,
        report.humidity,
        report.wind_speed
    FROM report
    WHERE report.station=p_station
    ORDER BY report.reception_time DESC
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_aggregated_report(p_station VARCHAR, p_start_date TIMESTAMP,p_end_date TIMESTAMP)
RETURNS TABLE(station VARCHAR,

    avg_temperature DECIMAL,
    min_temperature DECIMAL,
    max_temperature DECIMAL,

    avg_wind_speed DECIMAL,
    min_wind_speed DECIMAL,
    max_wind_speed DECIMAL,

    avg_humidity DECIMAL,
    min_humidity DECIMAL,
    max_humidity DECIMAL) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        report.station,

        AVG(report.temperature) AS avg_temperature,
        MIN(report.temperature) AS min_temperature,
        MAX(report.temperature) AS max_temperature,

        AVG(report.wind_speed) AS avg_wind_speed,
        MIN(report.wind_speed) AS min_wind_speed,
        MAX(report.wind_speed) AS max_wind_speed,

        AVG(report.humidity) AS avg_humidity,
        MIN(report.humidity) AS min_humidity,
        MAX(report.humidity) AS max_humidity

    FROM report
    WHERE report.station=p_station AND 
        (p_start_date IS NULL OR report.local_time > p_start_date) AND 
        (p_end_date IS NULL OR report.local_time < p_end_date)
    GROUP BY report.station
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_missingData()
RETURNS TABLE(
    station VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT DISTINCT report.station
    FROM report
    WHERE report.station NOT IN (
        SELECT report.station
        FROM report
        WHERE report.reception_time >= NOW() - INTERVAL '30 seconds'
    );
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_alert(threshold DECIMAL)
RETURNS TABLE(
    station VARCHAR,
    avg_temperature DECIMAL) AS $$
BEGIN
    RETURN QUERY
    SELECT report.station,
        AVG(report.temperature) AS avg_temperature
    FROM report
    WHERE report.local_time >= NOW() - INTERVAL '30 seconds'
    GROUP BY report.station
    HAVING AVG(report.temperature) > threshold;
END;
$$ LANGUAGE plpgsql;