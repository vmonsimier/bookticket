package com.bookticket.demo.reservation.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookticket.demo.reservation.domain.Reservation;
import com.bookticket.demo.reservation.domain.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }
}