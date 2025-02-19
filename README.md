# WeatherStations
Small API and database setup in docker.
API: Java Springboot 3.4.2 built with Maven
DB: Postgres  
  
# Credit
Made by Ennio Fernando Velásquez Berlingeri. 
Github Username - EfVelasquez
19-02-2025

Github Repository: https://github.com/EfVelasquez/WeatherStations
  
# Description
WeatherStations is a RESTful API service that registers weather reports. This project offer various ways of retreiving 
stored information, and counts with a small alert system that informs of the current status of the info received by
API calls during time.
This small sample project consists of a Spring Boot API, setup with Swagger and Quartz Jobs, and
a PostgreSQL database. The whole project is packaged in a Docker compose.
This project was made in the course of a couple of days, involving research and problem-solving of said technologies.  
Weather Stations serves as a demonstration of a containerized structure for an API service and Database with a correct  
and scalable structure, making use of Stored Procedures, Controllers, Handlers, and Repositories.

# Instructions  
  
This whole project was setup and packaged in docker, so first of all, you need to download Docker:  
https://www.docker.com/  
  
Once you have docker installed, you just need to open a command console and build the docker compose.  
Locate yourself in the app folder inside the project. And simply call the docker compose:  
Windows CMD example: "docker-compose up"
(You can also call "docker-compose up --build" to force a recompilation of the images)

You can use "docker-compose down --volumes" to delete the images and volumes created by the project.  
(But why would you want to do that anyways...?)   

Once you called the docker-compose, docker will take charge of the rest!  
It will setup the postgre database, build the Maven project to create the app binaries, create the docker image, and then launch it.  

By default, the project will route to localhost:8080.  
  
To open up the Swagger Api Documentation go to http://localhost:8080/swagger-ui/index.html#/ on your browser.  
There you can play with the endpoints and functionality.  
The alert system prints the alerts through standard output, so you can see the alerts in the console where you launched the docker   compose, or in Docker Desktop, in the app container's output. 
