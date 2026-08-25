package com.bookticket.demo.reservation.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

    public Reservation(Long eventId1, Long userId1, int nbTickets1, char category1) {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long eventId;
    private Long userId;
    private int nbTickets;
    private char category;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }


    public Long getEventId() {
        return eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public int getNbTickets() {
        return nbTickets;
    }

    public char getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}