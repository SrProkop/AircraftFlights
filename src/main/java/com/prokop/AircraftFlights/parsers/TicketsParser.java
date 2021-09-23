package com.prokop.AircraftFlights.parsers;

import com.prokop.AircraftFlights.models.Ticket;

import java.io.*;

import com.google.gson.*;
import com.prokop.AircraftFlights.models.Tickets;

public class TicketsParser {

    public static Ticket[] getTickets(String filePath) {
        return parser(filePath);
    }

    private static Ticket[] parser(String filePath) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Tickets tickets = new Tickets();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            while (reader.ready()) {
                stringBuilder.append(reader.readLine());
            }
            inputStream.close();
            tickets = gson.fromJson(stringBuilder.toString(), Tickets.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets.getTickets();
    }
}
