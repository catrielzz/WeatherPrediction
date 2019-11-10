package com.hnagorny.request.planetarysystem;

import com.hnagorny.request.spacebody.CentralStar;
import com.hnagorny.request.spacebody.SpaceBody;
import com.hnagorny.request.utils.WeatherState;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines methods for a Planetary System.
 * @author hnagorny
 */

public abstract class PlanetarySystem {

    protected SpaceBody sun = new CentralStar("Sun");
    protected List<SpaceBody> spaceBodies = new ArrayList<>();
    protected Double maxBoundarySize;

    /**
     * This constructor creates a Planetary System. It puts every Space Body received on a list.
     */

    public PlanetarySystem(SpaceBody... spaceBodies) {

        this.maxBoundarySize = 0D;

        for(SpaceBody spaceBody: spaceBodies) {

            this.spaceBodies.add(spaceBody);

        }

    }

    public Double getMaxBoundarySize() {

        return maxBoundarySize;

    }

    public abstract WeatherState getCurrentWeather();

    /**
     * This method calls move() for every Space Body on the list.
     */

    public void move() {

        for(SpaceBody spaceBody : spaceBodies) {

            spaceBody.move();

        }

    }

}
