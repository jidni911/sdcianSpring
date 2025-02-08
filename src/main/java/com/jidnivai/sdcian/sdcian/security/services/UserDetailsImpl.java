package com.jidnivai.sdcian.sdcian.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.enums.Gender;
import com.jidnivai.sdcian.sdcian.enums.UserStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String email;

  private String phoneNumber;

  private String address;

  private Set<Role> roles;

  private String fullName;

  private Gender gender;

  private LocalDate dob;

  @JsonIgnore
  private String password;

  private UserStatus status;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(
      Long id,
      String username,
      String email,
      String password,
      String phoneNumber,
      String address,
      Set<Role> roles,
      String fullName,
      Gender gender,
      LocalDate dob,
      UserStatus status,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.roles = roles;
    this.fullName = fullName;
    this.gender = gender;
    this.dob = dob;
    this.status = status;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        user.getPhoneNumber(),
        user.getAddress(),
        user.getRoles(),
        user.getFullName(),
        user.getGender(),
        user.getDob(),
        user.getStatus(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  
}
