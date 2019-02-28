# Deployment process

1) build with "mvn clean package"
2) find built artifact which is located in "application" module
3) run with "java -jar filename.jar", where filename is from 2nd step
4) you can override service port by entering any port in "-Dserver.port=1234" before "-jar" attribute


# Default run configuration

- port: 8085
- address: localhost

They can be changed in application.yml file


# Service URL

  http://localhost:port/v1/weather


# Request structure

- request must contain either "city" parameter or both "latitude" and "longitude". City chars' case is ignored.
    Moscow, St.Petersburg and New-York are implemented.
- optional parameter is "daysToForecast", default value is 1
- if city and both coordinates are specified, only coordinates are considered in request

so link could be http://localhost:8085/v1/weather?city=New-York
or http://localhost:8085/v1/weather?latitude=1&longitude=1&daysToForecast=5

# Response structure
- JSON with result from Yandex weather api
- JSON with error, describing what happened


# Yandex weather api documentation:
https://tech.yandex.ru/weather/doc/dg/concepts/forecast-docpage/

