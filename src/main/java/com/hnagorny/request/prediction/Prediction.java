package com.hnagorny.request.prediction;

import com.hnagorny.request.planetarysystem.PlanetarySystem;
import com.hnagorny.request.api.Weather;
import com.hnagorny.request.utils.WeatherState;

import java.util.HashMap;
import java.util.Map;

import static com.hnagorny.request.utils.WeatherState.*;

/**
 * This class predicts the Weather State for a Planetary System given a number of years and the system in question.
 * @author hnagorny
 */

public class Prediction {

    private static final int daysInAYear = 365;

    private int rainierDay;
    private Map<WeatherState, Integer> periods;
    private Map<Integer, String> history;
    private WeatherState[] states = WeatherState.values();

    /**
     * This constructor creates a Prediction and initialize it.
     */

    public Prediction(int yearsOfPrediction, PlanetarySystem planetarySystem) {

        this.rainierDay = -1;
        this.periods = new HashMap<>();
        this.history = new HashMap<>();

        initialize(yearsOfPrediction, planetarySystem);

    }

    /**
     * This method initializes the prediction receiving a number of year and a Planetary System. It performs a move each day of the prediction.
     * First, it puts each Weather State as a key on a HashMap with value 0.
     * Second, it verifies for every day if the Weather State is RAIN and if the prediction boundary size is bigger than the system's.
     * If it is, the current day would be the max rainy day and the current prediction boundary would be equal to the system's.
     * Finally, it increments every Weather State that be equal to the current. After that, it saves every day and his Weather State on a HashMap.
     */

    private void initialize(int yearsOfPrediction, PlanetarySystem planetarySystem) {

        Double predictionMaxBoundary = 0D;
        int daysOfPrediction = yearsOfPrediction * daysInAYear;

        for(int i = 0; i < WeatherState.values().length; i++) {

            periods.put(states[i], 0);

        }

        for(int currentDayPrediction = 0; currentDayPrediction < daysOfPrediction; currentDayPrediction++) {

            if(currentDayPrediction != 0)
                planetarySystem.move();

            WeatherState weather = planetarySystem.getCurrentWeather();
            Double maxPlanetarySystemBoundary = planetarySystem.getMaxBoundarySize();

            if(weather.equals(RAIN) && (predictionMaxBoundary < maxPlanetarySystemBoundary)) {

                rainierDay = currentDayPrediction;
                predictionMaxBoundary = maxPlanetarySystemBoundary;

            }

            periods.put(weather, periods.get(weather) + 1);
            history.put(currentDayPrediction, weather.toString());

        }

    }

    public Map<Integer, String> getHistory() {

        return history;

    }

    public Map<WeatherState, Integer> getPeriods() {

        return periods;

    }

    public Weather getRainierDay() {

        return new Weather(rainierDay, history.get(rainierDay));

    }

}
