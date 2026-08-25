package com.bookticket.demo.event.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String location;

    private LocalDateTime startedAt;

    private int capacity;

    private int availableTickets;

    private BigDecimal price;

    protected Event() {}
    
    public Event(String name, String description, String location, LocalDateTime startedAt, int capacity, int availableTickets, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.startedAt = startedAt;
        this.capacity = capacity;
        this.availableTickets = availableTickets;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public BigDecimal getPrice() {
        return price;
    }
}