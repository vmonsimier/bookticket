package com.bookticket.demo.reservation.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookticket.demo.reservation.domain.Reservation;

public interface JpaReservationRepository extends JpaRepository<Reservation, Long> {
    
}