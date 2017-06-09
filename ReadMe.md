
# Stormy Data

A seemingly simple sample app in Spring Boot 1.5 to demonstrate out Flyway migrations work

The source data CSV file is from https://catalog.data.gov/dataset/ncdc-storm-events-database


I used Postgres in Docker for the database. To install:

`docker run --name exp-postgres -e POSTGRES_PASSWORD=password -d postgres`

then load the data in it from `data.sql` (it will be in flyway.... someday)

Or you can load the data from the raw CSV file -- see the `python` folder.

Change `application.properties` to point to your postgres.

If you can get this to run in H2, so be it.

