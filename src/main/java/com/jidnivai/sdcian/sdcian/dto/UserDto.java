package com.jidnivai.sdcian.sdcian.dto;

import java.time.LocalDate;
import java.util.Set;

import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.enums.Gender;
import com.jidnivai.sdcian.sdcian.enums.UserStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String fullName;
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dob;
 
    private Set<Role> roles;

    private String phoneNumber;
    private String address;
    private Image profilePicture;
    
    private Image coverPicture;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
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
}
