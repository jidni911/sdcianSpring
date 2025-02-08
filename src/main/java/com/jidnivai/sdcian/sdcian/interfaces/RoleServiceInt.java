package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface RoleServiceInt {

    Page<User> getUsersByRoleName(String roleName, int page, int size);

    Set<Role> getRolesOfUser(Long id);

    Set<Role> assignRole(String roleName, Long id);

    Set<Role> removeRole(String roleName, Long id);

    List<Role> getRoles();

}
