package com.bookticket.demo.reservation.domain;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    void save(Reservation reservation);
    void delete(Reservation reservation);
    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
}