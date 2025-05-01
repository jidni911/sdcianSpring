package com.jidnivai.sdcian.sdcian.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.storage.Image;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isGroup = false;

    @ManyToOne
    private Image groupImage;

    
    @ManyToMany
    private List<User> requestedMembers;

    @ManyToMany
    private List<User> members;

    @ManyToOne
    private User creator;

    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private int messegeCount = 0;

    // @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Messege> messages;

    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = java.time.LocalDateTime.now();
        this.updatedAt = java.time.LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = java.time.LocalDateTime.now();
    }


}
