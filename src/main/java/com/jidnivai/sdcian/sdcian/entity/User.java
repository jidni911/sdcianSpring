package com.jidnivai.sdcian.sdcian.entity;

import java.time.LocalDate;
import java.util.Set;

import com.jidnivai.sdcian.sdcian.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;


    private Gender gender;
    private LocalDate dob;
    
    @ManyToMany
    private Set<Role> roles;

    private String phoneNumber;
    private String address;
    private String profilePicture;
    private String coverPicture;
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
