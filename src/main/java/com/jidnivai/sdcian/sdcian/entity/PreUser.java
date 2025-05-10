package com.jidnivai.sdcian.sdcian.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PreUser {
    
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
    

    private String phoneNumber;
    private String address;
    @OneToOne
    @ToString.Exclude
    private Image profilePicture;

    private String emailOTP;
    private LocalDateTime emailOtpExpiryTime;
    private Boolean emailVerified = false;
}
