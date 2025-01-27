package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.entity.Event;
import com.jidnivai.sdcian.sdcian.entity.Sponsor;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.EventServiceInt;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventServiceInt eventServiceInt;

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventServiceInt.getEvent(id);
    }

    @GetMapping
    public Page<Event> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return eventServiceInt.getEvents();
    }

    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        return eventServiceInt.addEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventServiceInt.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventServiceInt.deleteEvent(id);
    }

    @PostMapping("/requestSponsor")
    public Sponsor requestToBeSponsor(@RequestBody User sponsor) {
        return eventServiceInt.requestToBeSponsor(sponsor);
    }

}
