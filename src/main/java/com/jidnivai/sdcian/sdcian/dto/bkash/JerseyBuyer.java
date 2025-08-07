package com.jidnivai.sdcian.sdcian.dto.bkash;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class JerseyBuyer {
    @Id
    private String number;
    private String name;
    @JsonIgnore
    @ToString.Exclude
    private String password;
    private String role;
    private boolean active;
}
