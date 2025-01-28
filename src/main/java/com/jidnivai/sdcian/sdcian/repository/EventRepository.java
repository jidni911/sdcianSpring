package com.jidnivai.sdcian.sdcian.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByOrganiserId(Long id, PageRequest of);

    Page<Event> findByDateAfter(LocalDate now, PageRequest of);

}
