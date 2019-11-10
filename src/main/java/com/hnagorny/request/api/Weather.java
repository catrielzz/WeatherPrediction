package com.hnagorny.request.api;

/**
 * This class creates a Weather. It is built by a day and a state.
 * @author hnagorny
 */

public class Weather {

    private Integer day;
    private String state;

    public Weather(Integer day, String state) {

        this.day = day;
        this.state = state;

    }

    public Integer getDay() {

        return day;

    }

    public void setDay(Integer day) {

        this.day = day;

    }

    public String getState() {

        return state;

    }

    public void setState(String state) {

        this.state = state;

    }

}
