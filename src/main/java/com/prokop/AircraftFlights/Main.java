package com.prokop.AircraftFlights;

import com.prokop.AircraftFlights.calculate.CalculateForAircraftFlights;
import com.prokop.AircraftFlights.parsers.TicketsParser;
import com.prokop.AircraftFlights.models.Ticket;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    private static final String FILE_PATH = "file\\tickets.json";

    public static void main(String[] args) {
        Ticket[] tickets = TicketsParser.getTickets(FILE_PATH);
        String cityOne = "Владивосток";
        String cityTwo = "Тель-Авив";
        System.out.println("Среднее время полёта между городами " +
                cityOne + " и " + cityTwo + " = " + CalculateForAircraftFlights.getAverageTime(tickets, cityOne, cityTwo));
        System.out.println("90-й процентиль времени полета между " +
                cityOne + " и " + cityTwo + " = " +
                CalculateForAircraftFlights.getPercentile(tickets, cityOne, cityTwo, 90));
    }
}
