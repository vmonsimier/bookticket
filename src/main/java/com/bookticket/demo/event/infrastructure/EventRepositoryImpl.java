package com.bookticket.demo.event.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bookticket.demo.event.domain.Event;
import com.bookticket.demo.event.domain.EventRepository;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private final JpaEventRepository jpaEventRepository;

    public EventRepositoryImpl(JpaEventRepository jpaEventRepository) {
        this.jpaEventRepository = jpaEventRepository;
    }

    @Override
    public Event save(Event event) {
        return jpaEventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return jpaEventRepository.findById(id);
    }

    @Override
    public void delete(Event event) {
        jpaEventRepository.delete(event);
    }

    @Override
    public List<Event> findAll() {
        return jpaEventRepository.findAll();
    }
    
}