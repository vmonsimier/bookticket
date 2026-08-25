package com.bookticket.demo.event.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookticket.demo.event.domain.Event;
import com.bookticket.demo.event.domain.EventRepository;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public void delete(Event event) {
        eventRepository.delete(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}