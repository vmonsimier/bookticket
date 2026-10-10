package com.bookticket.demo.reservation.interfaces.dto;

public record CreateReservationRequest(
        Long eventId,
        int nbTickets, 
        char category
) {}
