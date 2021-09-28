package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AirTravelFilter airTravelFilter = AirTravelFilter.getAirTravelFilter();
        List<Flight> list = FlightBuilder.createFlights();
        System.out.println(airTravelFilter.beforeTime(list, LocalDateTime.now()));
        System.out.println(airTravelFilter.breakTimeMore(list,2));
        System.out.println(airTravelFilter.arrivalBeforeDeparture(list));
    }
}
