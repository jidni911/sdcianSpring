package com.jidnivai.sdcian.sdcian.service;

import com.jidnivai.sdcian.sdcian.entity.Event;
import com.jidnivai.sdcian.sdcian.entity.Sponsor;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.EventServiceInt;
import com.jidnivai.sdcian.sdcian.repository.EventRepository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class EventService implements EventServiceInt {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Event> getEvents() {
        return eventRepository.findAll(PageRequest.of(0, 10));
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Event oldEvent = eventRepository.findById(id).orElse(null);
        if (oldEvent == null) {
            return null;
        }
        oldEvent.setName(event.getName());
        oldEvent.setDescription(event.getDescription());
        oldEvent.setDate(event.getDate());
        oldEvent.setSponsor(event.getSponsor());
        return eventRepository.save(oldEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        eventRepository.delete(event);
    }

    @Override
    public Sponsor requestToBeSponsor(User sponsor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Event> getFutureEvents(PageRequest of) {
        return eventRepository.findByDateAfter(LocalDate.now(), of);
    }

    @Override
    public Page<Event> getEventsByUser(Long id, PageRequest of) {
        return eventRepository.findByOrganiserId(id, of);
    }
}

