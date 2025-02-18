
#
# Build stage
#
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY app/pom.xml .
COPY app/src ./src
RUN mvn -f pom.xml clean package -DskipTests

#
# Launch
#

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY app/target/WeatherStations.jar WeatherStations.jar

EXPOSE 8080

# API execution
CMD ["java", "-jar", "WeatherStations.jar"]