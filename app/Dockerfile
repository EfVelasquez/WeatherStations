
#
# Build stage
#
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY app/pom.xml .
COPY app/src ./src
RUN mvn -f pom.xml clean package -DskipTests

#
# Launch stage
#

FROM openjdk:17-jdk-slim AS launch

# Environment variable for alert temperature job
ENV ALERT_TEMPERATURE_THRESHOLD=38.5

WORKDIR /app

COPY --from=build app/target/WeatherStations.jar WeatherStations.jar

EXPOSE 8080

# API execution
CMD ["java", "-jar", "WeatherStations.jar"]