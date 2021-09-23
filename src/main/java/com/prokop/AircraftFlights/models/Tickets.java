package com.prokop.AircraftFlights.models;

import java.util.Arrays;

public class Tickets {
    private Ticket[] tickets;

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "tickets=" + Arrays.toString(tickets) +
                '}';
    }
}
