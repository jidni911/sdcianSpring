package com.jidnivai.sdcian.sdcian.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    // Finds roles by name (roleName)
    Page<Role> findByNameContaining(String roleName, Pageable pageable);

    // Finds roles by user ID, updated to Long
    Set<Role> findByUsersId(Long userId);//TODO there are two different tables

    // Finds role by exact role name
    Optional<Role> findByName(String roleName);

    Page<User> findByName(String roleName, Pageable pageable);
}
