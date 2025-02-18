
# Virtualization environment for Java 17
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/WeatherStations.jar WeatherStations.jar

EXPOSE 8080

# API execution
CMD ["java", "-jar", "WeatherStations.jar"]