package com.bookticket.demo.reservation.application;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bookticket.demo.reservation.domain.Reservation;
import com.bookticket.demo.reservation.domain.ReservationRepository;
import com.bookticket.demo.user.application.UserService;
import com.bookticket.demo.user.domain.User;

@Service
public class ReservationService {
    private final UserService userService;
    private final ReservationRepository reservationRepository;

    public ReservationService(UserService userService, ReservationRepository reservationRepository) {
        this.userService = userService;
        this.reservationRepository = reservationRepository;
    }

    public void save(Reservation reservation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName()).orElseThrow();
        reservation.setUserId(user.getId());
        reservationRepository.save(reservation);
    }


    public void delete(Reservation reservation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin) {
            User user = userService.findByEmail(authentication.getName()).orElseThrow();
            if(!Objects.equals(reservation.getUserId(), user.getId())) {
                throw new AccessDeniedException("You are not allowed to delete this reservation");
            }
        }
        reservationRepository.delete(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if(!reservationOptional.isEmpty()) {
            return Optional.empty();
        }

        if(!isAdmin) {
            User user = userService.findByEmail(authentication.getName()).orElseThrow();
            if(!Objects.equals(reservationOptional.get().getUserId(), user.getId())) {
                throw new AccessDeniedException("You are not allowed to view this reservation");
            }
        }
        return reservationOptional;
    }
}