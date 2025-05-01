package com.jidnivai.sdcian.sdcian.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.storage.Image;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Event {


    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Image coverImage;
    private String name;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private String fromLocation;

    private String toLocation;

    private LocalDateTime startTime;

    private Long duration;

    private Integer distanceInKM;

    private String description;

    @ManyToOne
    private User organiser;

    @ManyToMany
    private List<Sponsor> sponsor;

    @ManyToMany
    private List<User> sponsorShipRequests;

    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    
    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
