package com.prokop.AircraftFlights.calculate;

import com.prokop.AircraftFlights.models.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CalculateForAircraftFlights {

    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public static String getAverageTime(Ticket[] tickets, String cityOne, String cityTwo) {
        long minutesInFlight = 0;
        long averageTime = 0;
        long countFlight = 0;
        for (int i = 0; i < tickets.length; i++) {
            if ((tickets[i].getOrigin_name().equals(cityOne) && tickets[i].getDestination_name().equals(cityTwo))
                    || (tickets[i].getOrigin_name().equals(cityTwo) && tickets[i].getDestination_name().equals(cityOne))) {
                minutesInFlight += calculateMinutesFlights(tickets[i]);
                countFlight++;
            }
        }
        if (countFlight > 0) {
            averageTime = minutesInFlight / countFlight;
            return convertMinutesToHours(averageTime);
        } else {
            return "";
        }

    }

    public static String getPercentile(Ticket[] tickets, String cityOne, String cityTwo, int percentile) {
        List<Long> timeFlights = new ArrayList<>();
        for (int i = 0; i < tickets.length; i++) {
            if ((tickets[i].getOrigin_name().equals(cityOne) && tickets[i].getDestination_name().equals(cityTwo))
                    || (tickets[i].getOrigin_name().equals(cityTwo) && tickets[i].getDestination_name().equals(cityOne))) {
                timeFlights.add(calculateMinutesFlights(tickets[i]));
            }
        }
        Collections.sort(timeFlights);
        int index = (int) Math.ceil(percentile / 100.0 * timeFlights.size());
        return convertMinutesToHours(timeFlights.get(index));
    }

    private static Date getFullDate(String date, String time) {
        Date fullDate = new Date();
        try {
            fullDate = format.parse(date + " " + time);
            return fullDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fullDate;
    }

    private static Long calculateMinutesFlights(Ticket ticket) {
        Date departureFullDate = getFullDate(ticket.getDeparture_date(), ticket.getDeparture_time());
        Date arrivalFullDate = getFullDate(ticket.getArrival_date(), ticket.getArrival_time());
        return (arrivalFullDate.getTime() - departureFullDate.getTime()) / 60000;
    }

    private static String convertMinutesToHours(Long minutes) {
        return minutes/60 + "ч " + minutes % 60 + "м";

    }

}
