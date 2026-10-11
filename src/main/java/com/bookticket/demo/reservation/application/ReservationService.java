package com.bookticket.demo.reservation.application;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        Optional<User> userOptional = userService.findByEmail(authentication.getName());
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Ressource not found");
        }
        reservation.setUserId(userOptional.get().getId());
        reservationRepository.save(reservation);
    }


    public void delete(Reservation reservation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        Optional<User> userOptional = userService.findByEmail(authentication.getName());
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Ressource not found");
        }
        if(!isAdmin) {
            if(!Objects.equals(reservation.getUserId(), userOptional.get().getId())) {
                throw new AccessDeniedException("You are not allowed to delete this reservation");
            }
        }
        reservationRepository.delete(reservation);
    }

    public List<Reservation> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin) {
            throw new AccessDeniedException("You are not allowed to view all reservations");
        }
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if(!reservationOptional.isEmpty()) {
            throw new UsernameNotFoundException("Ressource not found");
        }

        Optional<User> userOptional = userService.findByEmail(authentication.getName());
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Ressource not found");
        }

        if(!isAdmin && !Objects.equals(reservationOptional.get().getUserId(), userOptional.get().getId())) {
            throw new AccessDeniedException("You are not allowed to view this reservation");
        }
        
        return reservationOptional;
    }
}