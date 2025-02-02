package com.jidnivai.sdcian.sdcian.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Role {

    @Id
    private String name; // Unique role name

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore 
    private List<User> users;

}
