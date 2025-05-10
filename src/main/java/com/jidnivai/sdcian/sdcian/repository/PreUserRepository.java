package com.jidnivai.sdcian.sdcian.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.sdcian.sdcian.entity.PreUser;

public interface PreUserRepository extends JpaRepository<PreUser, Long> {

    Optional<PreUser> findByEmail(String email);

    Optional<PreUser> findByUsername(String username);
    
}
