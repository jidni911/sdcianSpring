package com.jidnivai.sdcian.sdcian.security.services;

import com.jidnivai.sdcian.sdcian.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private User user;


  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(
      User user,
      Collection<? extends GrantedAuthority> authorities) {
    this.user = user;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user,
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
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
