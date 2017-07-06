
# Stormy Data

A seemingly simple sample app in Spring Boot 1.5 to demonstrate how Flyway migrations work

The source data CSV file is from https://catalog.data.gov/dataset/ncdc-storm-events-database

I used Postgres in Docker for the database. To install:

`docker run --name exp-postgres -e POSTGRES_PASSWORD=password -d postgres`

Then change the `application.properties` to your proper IP address.


