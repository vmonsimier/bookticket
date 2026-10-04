package com.bookticket.demo.reservation.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bookticket.demo.reservation.domain.Reservation;
import com.bookticket.demo.reservation.domain.ReservationRepository;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
    private final JpaReservationRepository jpaReservationRepository;

    public ReservationRepositoryImpl(JpaReservationRepository jpaReservationRepository) {
        this.jpaReservationRepository = jpaReservationRepository;
    }

    @Override
    public void save(Reservation reservation) {
        jpaReservationRepository.save(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        jpaReservationRepository.delete(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return jpaReservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return jpaReservationRepository.findById(id);
    }
}