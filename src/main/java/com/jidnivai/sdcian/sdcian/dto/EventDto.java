package com.jidnivai.sdcian.sdcian.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.Sponsor;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class EventDto {
    private Long id;

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

    private UserDto organiser;

    private List<Sponsor> sponsor;
}
