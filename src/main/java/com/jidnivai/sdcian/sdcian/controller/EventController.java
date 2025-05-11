package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.dto.EventCreateDto;
import com.jidnivai.sdcian.sdcian.dto.EventDto;
import com.jidnivai.sdcian.sdcian.entity.Event;
import com.jidnivai.sdcian.sdcian.entity.Sponsor;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.EventServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

    @Autowired
    private EventServiceInt eventServiceInt;

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        try {
            return eventServiceInt.getEvent(id);
        } catch (Exception e) {
            System.out.println("EventController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping
    public Page<Event> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return eventServiceInt.getEvents(user.getUser(), PageRequest.of(page, size));
        } catch (Exception e) {
            System.out.println("EventController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<EventDto> addEvent(
        @RequestBody EventCreateDto eventCreateDto,
        @AuthenticationPrincipal UserDetailsImpl user
    ) {
        try {
            return ResponseEntity.ok(eventServiceInt.addEvent(eventCreateDto, user.getUser()));
        } catch (Exception e) {
            System.out.println("EventController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        try {
            return eventServiceInt.updateEvent(id, event);
        } catch (Exception e) {
            System.out.println("EventController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        try {
            eventServiceInt.deleteEvent(id);
        } catch (Exception e) {
            System.out.println("EventController: " + e.getMessage());
        }
    }

    @PostMapping("/requestSponsor")
    public Sponsor requestToBeSponsor(@RequestBody User sponsor) {
        try {
            return eventServiceInt.requestToBeSponsor(sponsor);
        } catch (Exception e) {
            System.out.println("EventController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/future")
    public Page<Event> getFutureEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return eventServiceInt.getFutureEvents(PageRequest.of(page, size));
        } catch (Exception e) {
            System.out.println("EventController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/past")
    public Page<Event> getPastEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return eventServiceInt.getFutureEvents(PageRequest.of(page, size));
        } catch (Exception e) {
            System.out.println("EventController: " + e.getMessage());
            return null;
        }
    }

    
    @GetMapping("/user/{id}")
    public Page<Event> getEventsByUser(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return eventServiceInt.getEventsByUser(id, PageRequest.of(page, size));
        } catch (Exception e) {
            System.out.println("EventController: " + e.getMessage());
            return null;
        }
    }

}

