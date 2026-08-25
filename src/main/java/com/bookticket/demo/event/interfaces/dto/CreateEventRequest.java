package com.bookticket.demo.event.interfaces.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateEventRequest(
    String name,
    String description,
    String location,
    LocalDateTime startedAt,
    int capacity,
    BigDecimal price
) {
}