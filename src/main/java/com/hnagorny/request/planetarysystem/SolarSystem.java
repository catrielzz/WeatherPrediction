package com.hnagorny.request.planetarysystem;

import com.hnagorny.request.spacebody.Planet;
import com.hnagorny.request.spacebody.SpaceBody;
import com.hnagorny.request.utils.Coordinate;
import com.hnagorny.request.utils.WeatherState;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.HashSet;
import java.util.Set;

import static com.hnagorny.request.utils.WeatherState.*;
import static org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE;

/**
 * This class creates a Planetary System and implements getCurrentWeather for every system created.
 * @author hnagorny
 */

public class SolarSystem extends PlanetarySystem {

    private static final Double hyperplaneThickness = 0.01D;
    Set<Vector2D> vectors = new HashSet<>();

    /**
     * This constructor creates a Solar System. It calls the PlanetarySystem's constructor to create one.
     * After that, a HashSet adds vectors represented with the coordinates of every planet on the list.
     * If HashSet's size is lower than 3, it means that there are planets with the same coordinates so the program throws an exception.
     */

    public SolarSystem(Planet planet1, Planet planet2, Planet planet3) {

        super(planet1, planet2, planet3);

        for(SpaceBody spaceBody : spaceBodies) {

            vectors.add(new Vector2D(spaceBody.getCoordinate().getX(), spaceBody.getCoordinate().getY()));

        }

        if(vectors.size() < 3) {

            throw new RuntimeException("There are 2 or more planets started in the same position.");

        }

    }

    /**
     * This method obtains the current weather on the system.
     * First, it takes the coordinates of the Central Star and every Space Body on the list to construct a vector with each one of them.
     * Second, a Line is created using the vectors of planet1 and planet2 to ask if it contains planet3.
     * If it contains, the new ask would be if the Line contains the Central Star too. If it contains, the Weather State would be DROUGHT or OPTIMAL if it doesn't.
     * Finally, a Polygon is created using a Double for the thickness and the three vectors of the planets to ask if the Central Star is INSIDE him.
     * If it is, the Weather State would be RAIN and if the actual boundary size is bigger than the initial, it would be updated.
     * If the Central Star is not INSIDE, the Weather State would be NORMAL.
     * @return WeatherState
     */

    @Override
    public WeatherState getCurrentWeather() {

        Coordinate coordP1 = spaceBodies.get(0).getCoordinate();
        Coordinate coordP2 = spaceBodies.get(1).getCoordinate();
        Coordinate coordP3 = spaceBodies.get(2).getCoordinate();

        Vector2D vecP1 = new Vector2D(coordP1.getX(), coordP1.getY());
        Vector2D vecP2 = new Vector2D(coordP2.getX(), coordP2.getY());
        Vector2D vecP3 = new Vector2D(coordP3.getX(), coordP3.getY());
        Vector2D vecCentral = new Vector2D(sun.getCoordinate().getX(), sun.getCoordinate().getY());

        Line linePlanet1Planet2 = new Line(vecP1, vecP2);

        if(linePlanet1Planet2.contains(vecP3)) {

            return linePlanet1Planet2.contains(vecCentral) ? DROUGHT : OPTIMAL;

        }

        PolygonsSet triangle = new PolygonsSet(hyperplaneThickness, vecP1, vecP2, vecP3);

        if(triangle.checkPoint(vecCentral).equals(INSIDE)) {

            Double actualBoundarySize = triangle.getBoundarySize();

            maxBoundarySize = actualBoundarySize > maxBoundarySize ? actualBoundarySize : maxBoundarySize;

            return RAIN;

        }

        return NORMAL;

    }

}
