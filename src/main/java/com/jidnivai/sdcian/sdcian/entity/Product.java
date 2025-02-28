package com.jidnivai.sdcian.sdcian.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {

  

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String category;

    private String brand;

    private Float price;

    private Float discountPrice;

    private Float buyingPrice;

    private String stockStatus;// Availabe, Low , Out of Stock

    private int quantity;

    @ManyToOne
    private Image mainImage;

    @ManyToMany
    private List<Image> galleryImages;

    private String dimensions;

    private String weight;

    @ManyToOne
    private User seller;

    private Float ratings = 0.0f;

    private int reviews;

    private String tags;

    private String origin;

    private String features;

    private LocalDateTime addedDate;

    private LocalDateTime updatedAt;

    
    @PrePersist
    public void prePersist(){
        this.addedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
