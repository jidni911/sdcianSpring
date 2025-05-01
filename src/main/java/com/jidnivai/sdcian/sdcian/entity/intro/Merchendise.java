package com.jidnivai.sdcian.sdcian.entity.intro;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.storage.Image;

@Getter
@Setter
@Entity
public class Merchendise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    private Home home;

    @OneToMany
    private List<Image> images;
}
