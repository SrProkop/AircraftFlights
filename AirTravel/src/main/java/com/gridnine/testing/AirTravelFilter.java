package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AirTravelFilter implements Filter<Flight> {

    private static AirTravelFilter airTravelFilter;

    private AirTravelFilter() {
    }

    public static AirTravelFilter getAirTravelFilter() {
        if (airTravelFilter == null) {
            airTravelFilter = new AirTravelFilter();
        }
        return airTravelFilter;
    }

    @Override
    public List<Flight> beforeTime(List<Flight> list, LocalDateTime dateTime) {
        return list.stream()
                .filter(flight -> flight.getSegments().get(0).getDepartureDate().isBefore(dateTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> afterTime(List<Flight> list, LocalDateTime dateTime) {
        return list.stream()
                .filter(flight -> flight.getSegments().get(0).getDepartureDate().isAfter(dateTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> breakTimeMore(List<Flight> list, long hours) {
        list = list.stream()
                .filter(flight -> getBreakTime(flight.getSegments()) > hours)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Flight> breakTimeLess(List<Flight> list, long hours) {
        list = list.stream()
                .filter(flight -> getBreakTime(flight.getSegments()) < hours)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Flight> arrivalBeforeDeparture(List<Flight> list) {
        return list.stream()
                .filter(flight -> isValidFlight(flight.getSegments()))
                .collect(Collectors.toList());
    }

    private long getBreakTime(List<Segment> list) {
        if (list.size() <= 1) {
            return 0;
        }
        long breakTime = 0;
        for (int i = 1; i < list.size(); i++) {
            breakTime += Duration.between(list.get(i - 1).getArrivalDate(),
                            list.get(i).getDepartureDate())
                    .getSeconds() / 3600;
        }
        return breakTime;
    }

    private boolean isValidFlight(List<Segment> list) {
        return list.stream()
                        .anyMatch(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate()));
    }
}