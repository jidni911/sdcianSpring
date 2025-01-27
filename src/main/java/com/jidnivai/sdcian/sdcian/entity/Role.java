package com.jidnivai.sdcian.sdcian.entity;

import java.util.List;

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
    private String name;

    @ManyToMany
    private List<User> users;
}
