package com.hnagorny.request.spacebody;

import com.hnagorny.request.utils.Coordinate;

/**
 * This interface defines methods for every SpaceBody.
 * @author hnagorny
 */

public interface SpaceBody {

    Coordinate getCoordinate();

    void move();

}
