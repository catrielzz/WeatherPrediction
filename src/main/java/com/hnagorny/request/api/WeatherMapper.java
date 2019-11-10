package com.hnagorny.request.api;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class obtains the values from a query to build a Weather object.
 * @author hnagorny
 */

public class WeatherMapper implements RowMapper<Weather> {

    public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Weather(rs.getInt("day"), rs.getString("state"));

    }

}
