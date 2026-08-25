package com.bookticket.demo.reservation.interfaces.dto;

import java.time.LocalDateTime;

public record ReservationResponse(
    Long id,
    Long eventId,
    Long userId,
    int nbTickets,
    char category,
    LocalDateTime createdAt
) {}