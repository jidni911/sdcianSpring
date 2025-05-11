package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.jidnivai.sdcian.sdcian.dto.EventCreateDto;
import com.jidnivai.sdcian.sdcian.dto.EventDto;
import com.jidnivai.sdcian.sdcian.entity.Event;
import com.jidnivai.sdcian.sdcian.entity.Sponsor;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface EventServiceInt {

    Event getEvent(Long id);

    Page<Event> getEvents( User user, PageRequest of);

    EventDto addEvent(EventCreateDto eventCreateDto, User user);

    Event updateEvent(Long id, Event event);

    void deleteEvent(Long id);

    Sponsor requestToBeSponsor(User sponsor);

    Page<Event> getFutureEvents(PageRequest of);

    Page<Event> getEventsByUser(Long id, PageRequest of);

}
