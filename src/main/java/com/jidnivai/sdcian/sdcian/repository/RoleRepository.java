package com.jidnivai.sdcian.sdcian.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Page<User> findByName(String roleName, Pageable pageable);

    Set<Role> findByUserId(String id);

    Role findByRoleName(String roleName);

}
