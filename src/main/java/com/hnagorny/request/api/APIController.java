package com.hnagorny.request.api;

import com.hnagorny.request.planetarysystem.SolarSystem;
import com.hnagorny.request.prediction.Prediction;
import com.hnagorny.request.spacebody.Planet;
import com.hnagorny.request.utils.WeatherState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * This class manages the application's endpoints.
 * @author hnagorny
 */

@RestController
public class APIController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "weather";
    private final String DAY = "day";
    private final String STATE = "state";

    private Prediction prediction = new Prediction(10, new SolarSystem(
            new Planet("Ferengi", 1, 500, true),
            new Planet("Betasoide", 3, 2000, true),
            new Planet("Vulcano", 5, 1000, false)));

    Boolean initialized = false;

    /**
     * This is the endpoint to obtain the rainier day.
     * @return Weather
     */

    @RequestMapping("/rainierDay")
    public Weather rainierDay() {

        initializeDB();
        return prediction.getRainierDay();

    }

    /**
     * This is the endpoint to obtain the weather periods.
     * @return Map<WeatherState
     */

    @RequestMapping("/periods")
    public Map<WeatherState, Integer> periods() {

        initializeDB();
        return prediction.getPeriods();

    }

    /**
     * This is the endpoint to obtain the weather history.
     * @return List<Weather>
     */

    @RequestMapping("/history")
    public List<Weather> history() {

        initializeDB();

        StringBuilder sql = new StringBuilder(50);
        sql.append("SELECT * FROM ");
        sql.append(TABLE);

        return jdbcTemplate.query(sql.toString(), new WeatherMapper());

    }

    /**
     * This is endpoint the to obtain the prediction of a specific day.
     * @return List<Weather>
     */

    @RequestMapping("/weather")
    public List<Weather> getWeatherStateByDay(@RequestParam(value = "day") int day) throws Exception {

        initializeDB();

        if(day > -1 && day < 3650) {

            StringBuilder sql = new StringBuilder(50);
            sql.append("SELECT * FROM ");
            sql.append(TABLE + " WHERE day=");
            sql.append(day);

            return jdbcTemplate.query(sql.toString(), new WeatherMapper());

        } else {

            throw new Exception("Day out of prediction range.");

        }

    }

    /**
     * This method creates a table on the DB and puts the weather state of all days on it.
     */

    public void initializeDB() {

        if(!initialized) {

            jdbcTemplate.execute(format("CREATE TABLE %s(%s INTEGER, %s VARCHAR (10))", TABLE, DAY, STATE));

            for (int key : prediction.getHistory().keySet()) {

                jdbcTemplate.execute(format("INSERT INTO %s VALUES (%s, '%s')", TABLE, key, prediction.getHistory().get(key)));

            }

            initialized = true;

        }

    }

}