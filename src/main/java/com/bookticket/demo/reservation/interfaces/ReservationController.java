package com.bookticket.demo.reservation.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookticket.demo.reservation.application.ReservationService;
import com.bookticket.demo.reservation.domain.Reservation;
import com.bookticket.demo.reservation.interfaces.dto.CreateReservationRequest;
import com.bookticket.demo.reservation.interfaces.dto.ReservationResponse;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponse> findAll() {
        List<Reservation> reservations = reservationService.findAll();
        return reservations.stream().map(reservation -> new ReservationResponse(reservation.getId(), reservation.getEventId(), reservation.getUserId(), reservation.getNbTickets(), reservation.getCategory(), reservation.getCreatedAt())).toList();
    }

    @GetMapping("/{id}")
    public ReservationResponse findById(@PathVariable Long id) {
        Reservation reservation = reservationService.findById(id).orElseThrow();
        return new ReservationResponse(reservation.getId(), reservation.getEventId(), reservation.getUserId(), reservation.getNbTickets(), reservation.getCategory(), reservation.getCreatedAt());
    }
    @PostMapping
    public void save(@RequestBody CreateReservationRequest reservation) {
        Reservation reservationToSave = new Reservation(reservation.eventId(), reservation.userId(), reservation.nbTickets(), reservation.category());
        reservationService.save(reservationToSave);
    }

    @DeleteMapping
    public void delete(@RequestBody CreateReservationRequest reservation) {
        Reservation reservationToDelete = new Reservation(reservation.eventId(), reservation.userId(), reservation.nbTickets(), reservation.category());
        reservationService.delete(reservationToDelete);
    }
}
