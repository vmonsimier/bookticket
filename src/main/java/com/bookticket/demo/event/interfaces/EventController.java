package com.bookticket.demo.event.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookticket.demo.event.application.EventService;
import com.bookticket.demo.event.domain.Event;
import com.bookticket.demo.event.interfaces.dto.CreateEventRequest;
import com.bookticket.demo.event.interfaces.dto.EventResponse;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventResponse> getAll() {
        List<Event> events = this.eventService.findAll();
        return events.stream().map(
            event -> new EventResponse(
                event.getId(), 
                event.getName(), 
                event.getDescription(), 
                event.getLocation(), 
                event.getStartedAt(), 
                event.getCapacity(), 
                event.getAvailableTickets(),
                event.getPrice())
        ).toList();
    }

    @GetMapping("/{id}")
    public EventResponse getById(@PathVariable Long id) {
        Event event = this.eventService.findById(id).orElseThrow();
        return new EventResponse(
            event.getId(), 
            event.getName(), 
            event.getDescription(), 
            event.getLocation(), 
            event.getStartedAt(), 
            event.getCapacity(),
            event.getAvailableTickets(),
            event.getPrice()
        );
    }


    @PostMapping
    public Event create(@RequestBody CreateEventRequest event) {
        Event eventToSave = new Event(event.name(), event.description(), event.location(), event.startedAt(), event.capacity(), event.capacity(), event.price());
        return this.eventService.save(eventToSave);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Event> event = this.eventService.findById(id);
        if (event.isPresent()) {
            this.eventService.delete(event.get());
        }
    }
}