package com.bookticket.demo.event.interfaces.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventResponse(
    Long id,
    String name,
    String description,
    String location,
    LocalDateTime startedAt,
    int capacity,
    int availableTickets,
    BigDecimal price
) {
}