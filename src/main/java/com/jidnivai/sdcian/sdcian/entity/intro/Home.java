package com.jidnivai.sdcian.sdcian.entity.intro;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.storage.Image;

@Getter
@Setter
@ToString
@Entity
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String welcomeText;

    @OneToOne
    private Image welcomeImage;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
    private List<Special> specials;

    @OneToMany
    private List<Image> galleryImages;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
    private List<Sponsors> sponsors;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
    private List<Merchendise> merchendises;
}
