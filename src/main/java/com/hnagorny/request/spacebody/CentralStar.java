package com.hnagorny.request.spacebody;

import com.hnagorny.request.utils.Coordinate;

/**
 * This class implements methods from SpaceBody for the Central Star of the Planetary System.
 * @author hnagorny
 */

public class CentralStar implements SpaceBody {

    private String name;
    private Coordinate coordinate;

    /**
     * This constructor creates a Central Star and sets his coordinates to 0.
     */

    public CentralStar(String name) {

        this.name = name;
        coordinate = new Coordinate(0D,0D);

    }

    @Override
    public Coordinate getCoordinate() {

        return coordinate;

    }

    @Override
    public void move() {

    }

}
