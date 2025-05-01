package com.jidnivai.sdcian.sdcian.entity.merchandise;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Jersey {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Image> images; 
    private String title;
    private String description;
    private Double halfSleevePrice;
    private Double fullSleevePrice;
    private Double babyHalfSleevePrice;
    private Double babyFullSleevePrice;
    private Double customHalfSleevePrice;
    private Double customFullSleevePrice;
    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;
    @ManyToOne
    @JsonIgnore
    private User createdBy;
    @JsonIgnore
    @ManyToOne
    private User updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    @Transient
    private List<Long> imageIds;

}
