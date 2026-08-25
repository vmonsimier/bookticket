package com.bookticket.demo.event.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookticket.demo.event.domain.Event;

public interface JpaEventRepository extends JpaRepository<Event, Long> {
}