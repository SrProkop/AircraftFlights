package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AirTravelFilterTest {

    private static AirTravelFilter airTravelFilter = AirTravelFilter.getAirTravelFilter();
    private static List<Flight> flightList = FlightBuilder.createFlights();

    @Test
    void beforeTimeTest() {
        List<Flight> listOne = Arrays.asList(flightList.get(2));
        List<Flight> listTwo = airTravelFilter.beforeTime(flightList, LocalDateTime.now());
        Assertions.assertEquals(listOne, listTwo);
    }

    @Test
    void afterTimeTest() {
        List<Flight> listOne = new ArrayList<>(flightList);
        listOne.remove(flightList.get(2));
        List<Flight> listTwo = airTravelFilter.afterTime(flightList, LocalDateTime.now());
        Assertions.assertEquals(listOne, listTwo);
    }

    @Test
    void breakTimeMoreTest() {
        List<Flight> listOne = Arrays.asList(flightList.get(4), flightList.get(5));
        List<Flight> listTwo = airTravelFilter.breakTimeMore(flightList, 2);
        Assertions.assertEquals(listOne, listTwo);
    }

    @Test
    void breakTimeLessTest() {
        List<Flight> listOne = Arrays.asList( flightList.get(0), flightList.get(1), flightList.get(2), flightList.get(3));
        List<Flight> listTwo = airTravelFilter.breakTimeLess(flightList, 2);
        Assertions.assertEquals(listOne, listTwo);
    }

    @Test
    void arrivalBeforeDepartureTest() {
        List<Flight> listOne = Arrays.asList(flightList.get(3));
        List<Flight> listTwo = airTravelFilter.arrivalBeforeDeparture(flightList);
        Assertions.assertEquals(listOne, listTwo);
    }
}
