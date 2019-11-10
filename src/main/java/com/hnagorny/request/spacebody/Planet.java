package com.hnagorny.request.spacebody;

import com.hnagorny.request.utils.Coordinate;

import static java.lang.Math.*;

/**
 * This class implements methods from SpaceBody for every planet of the Planetary System. Additionally, every planet has a current angle, angle velocity, radius and a direction.
 * @author hnagorny
 */

public class Planet implements SpaceBody {

    private String name;
    private int currentAngle;
    private int angVel;
    private int radius;
    private boolean direction;

    /**
     * This constructor creates a Planet.
     */

    public Planet (String name, int angVel, int radius, boolean direction) {

        this.name = name;
        this.currentAngle = 0;
        this.angVel = abs(angVel);
        this.radius = abs(radius);
        this.direction = direction;

    }

    /**
     * This method obtains the coordinates of the planet.
     * x = cos(angle) * radius
     * y = sin(angle) * radius
     * @return Coordinate
     */

    @Override
    public Coordinate getCoordinate() {

        return new Coordinate(radius * cos(getAngleInRadians()),radius * sin(getAngleInRadians()));

    }

    /**
     * This method sets the current angle of the planet.
     */

    @Override
    public void move() {

        int nextAngle = currentAngle + angVel;
        currentAngle = nextAngle < 360 ? nextAngle : nextAngle - 360;

    }

    /**
     * This method obtains the current angle in radians.
     * @return Double
     */

    private Double getAngleInRadians() {

        return toRadians(direction ? -currentAngle : currentAngle);

    }

}
