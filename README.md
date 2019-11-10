# WeatherPrediction

Vulcan's REST API that predicts the state of each day's weather for the next 10 years.

- Open the project in an IDE and run "Application" or just paste the following line in IDE's terminal.

````
mvn spring-boot:run
````

- To obtain the weather periods enter in:
````
http://localhost:8080/periods
````

- To obtain the weather history enter in:
````
http://localhost:8080/history
````

- To obtain the rainier day enter in:
````
http://localhost:8080/rainierDay
````

- To obtain the weather state for an specific day enter in:
````
http://localhost:8080/weather?day=*
````
NOTE: "*" must be replaced for the number of a day between 0 and 3649