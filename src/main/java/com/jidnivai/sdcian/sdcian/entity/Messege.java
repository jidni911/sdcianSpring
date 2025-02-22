package com.jidnivai.sdcian.sdcian.entity;

import java.time.LocalDateTime;
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

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
@Getter
@Setter
@ToString
public class Messege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    @OneToMany
    private List<Image> images;
    @OneToMany
    private List<Video> videos;
    @OneToMany
    private List<Audio> audios;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private boolean isRead = false;

    private boolean isDeleted = false;

    private boolean isArchived = false;

    private boolean isSpam = false;

    private boolean isImportant = false;

    private boolean isStarred = false;

    private boolean isSent = false;

    private boolean isDraft = false;

    private boolean isTrash = false;

    private boolean isMuted = false;

    private boolean isBlocked = false;

    private boolean isDeletedForAll = false;

    private boolean isPinned = false;

    private boolean isDeletedForSender = false;

    private boolean isDeletedForReceiver = false;

    private boolean isDeletedForBoth = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
