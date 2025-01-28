package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.Set;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface RoleServiceInt {

    Page<User> getUsersByRoleName(String roleName, int page, int size);

    Set<Role> getRolesOfUser(String id);

    void assignRole(String roleName, Long id);

    void removeRole(String roleName, Long id);

}
