package com.jidnivai.sdcian.sdcian.entity.storage;


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
public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;
    
    private String path;

    private String url;//TODO controller, Service, DTO

    private String name;

    @Transient
    protected byte[] audioBytes;

    private String description;
    
    // @ManyToOne
    // private Post post;

}
