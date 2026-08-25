package com.bookticket.demo.reservation.interfaces.dto;

public record CreateReservationRequest(
        Long eventId,
        Long userId, 
        int nbTickets, 
        char category
) {}
