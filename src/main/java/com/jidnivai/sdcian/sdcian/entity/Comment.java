package com.jidnivai.sdcian.sdcian.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String commentText;

    @OneToMany
    private List<Image> image;
    @OneToMany
    private List<Video> video;

    @ManyToOne
    private Comment parent;

    @OneToMany
    private List<Comment> replies;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
