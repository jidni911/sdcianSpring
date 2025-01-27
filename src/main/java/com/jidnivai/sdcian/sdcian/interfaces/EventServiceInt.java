package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Event;
import com.jidnivai.sdcian.sdcian.entity.Sponsor;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface EventServiceInt {

    Event getEvent(Long id);

    Page<Event> getEvents();

    Event addEvent(Event event);

    Event updateEvent(Long id, Event event);

    void deleteEvent(Long id);

    Sponsor requestToBeSponsor(User sponsor);

}
