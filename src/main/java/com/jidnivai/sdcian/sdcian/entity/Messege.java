package com.jidnivai.sdcian.sdcian.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Messege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Chat chat;

    @OneToMany
    private List<Image> images;
    @OneToMany
    private List<Video> videos;
    @OneToMany
    private List<Audio> audios;

    @ManyToOne
    private User sender;

    private boolean isRead = false;

    private boolean isDeleted = false;

    private boolean isSent = false;
    // private boolean isPinned = false;
    // private boolean isArchived = false;

    // private boolean isSpam = false;

    // private boolean isImportant = false;

    // private boolean isStarred = false;


    // private boolean isDraft = false;

    // private boolean isTrash = false;

    // private boolean isMuted = false;

    // private boolean isBlocked = false;

    // private boolean isDeletedForAll = false;


    // private boolean isDeletedForSender = false;

    // private boolean isDeletedForReceiver = false;

    // private boolean isDeletedForBoth = false;

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

    public Messege(Long id,String message, Chat chat,  User sender, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.message = message;
        this.chat = chat;
        this.sender = sender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
