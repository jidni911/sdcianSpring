package com.jidnivai.sdcian.sdcian.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.entity.storage.Video;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User creator;

    private String postText;

    private String location;

    @ManyToMany
    private List<Image> postImage;

    @ManyToMany
    private List<Video> postVideo;

    @ManyToOne
    private Post sharedPost;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany
    private List<User> likers;

    @ManyToMany
    private List<User> reporter;

    @ManyToMany
    private List<User> shares;

    @ManyToMany
    private List<User> views;

    @ManyToMany
    private List<Product> products;

    private boolean isPublic = true;

    private boolean isDeleted = false;

    private boolean isArchived = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }




}
