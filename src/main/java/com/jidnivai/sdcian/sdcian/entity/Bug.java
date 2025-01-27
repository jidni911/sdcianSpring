package com.jidnivai.sdcian.sdcian.entity;

import com.jidnivai.sdcian.sdcian.enums.Severity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    private String steps;

    private boolean isSolved = false;

    @ManyToOne
    @ToString.Exclude
    private User submittedBy;
}
