package com.jidnivai.sdcian.sdcian.entity.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jidnivai.sdcian.sdcian.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;
    private String name;
    private String url;
    @Transient
    protected byte[] videoBytes;
    private String description;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private User user;
}
