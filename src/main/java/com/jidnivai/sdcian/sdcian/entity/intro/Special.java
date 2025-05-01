package com.jidnivai.sdcian.sdcian.entity.intro;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;

@Getter
@Setter
@Entity
public class Special {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Lob
    private String description;

    @ManyToOne
    @JsonIgnore
    private Home home;

    @OneToMany
    private List<Image> images;
}
