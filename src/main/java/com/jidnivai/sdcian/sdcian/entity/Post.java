package com.jidnivai.sdcian.sdcian.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany
    private List<Image> postImage;

    @OneToMany
    private List<Video> postVideo;

    @ManyToOne
    private Post sharedPost;

    @OneToMany
    private List<Comment> comments;

    @ManyToMany
    private List<User> likers;

    @ManyToMany
    private List<User> shares;

    @ManyToMany
    private List<User> views;

    @ManyToMany
    private List<Product> products;

    private boolean isPublic = true;

    private boolean isDeleted = false;

    private boolean isArchived = false;



}
