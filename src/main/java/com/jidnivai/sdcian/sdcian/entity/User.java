package com.jidnivai.sdcian.sdcian.entity;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jidnivai.sdcian.sdcian.dto.UserDto;
import com.jidnivai.sdcian.sdcian.entity.storage.Audio;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.enums.Gender;
import com.jidnivai.sdcian.sdcian.enums.UserStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String username;
    private String email;
    @JsonIgnore
    private String password;


    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dob;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "role_users")
    private Set<Role> roles;

    private String phoneNumber;
    private String address;
    @OneToOne
    @ToString.Exclude
    private Image profilePicture;
    
    @OneToOne
    @ToString.Exclude
    private Image coverPicture;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.INACTIVE;

    @ManyToOne
    private Audio profileMusic;

    private String about;
    private String website;
    private String facebook;
    private String instagram;
    private String twitter;
    private String youtube;
    private String github;
    private String linkedin;
    private String pinterest;
    private String tiktok;
    private String snapchat;
    private String telegram;
    private String whatsapp;
    private String discord;
    private String reddit;
    public UserDto toDto() {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(this, userDto);
        return userDto;
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this); // Ensure bidirectional consistency
    }
}
