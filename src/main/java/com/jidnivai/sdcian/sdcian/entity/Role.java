package com.jidnivai.sdcian.sdcian.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore 
    @ToString.Exclude
    private List<User> users;

    public void removeUser(User user) {
        this.users.remove(user);
        user.getRoles().remove(this); // Ensure bidirectional consistency
    }

}
