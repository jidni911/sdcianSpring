package com.jidnivai.sdcian.sdcian.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventCreateDto {

    private String name;

    private Long coverImage;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private String fromLocation;

    private String toLocation;

    private LocalDateTime startTime;

    private Long duration;

    private Integer distanceInKM;

    private String description;
}
