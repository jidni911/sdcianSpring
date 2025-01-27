package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;
import java.util.Set;

import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface RoleServiceInt {

    List<User> getUsersByRoleName(String roleName);

    Set<Role> getRolesOfUser(String id);

    Set<Role> assignRole(String roleName, String id);

    Set<Role> removeRole(String roleName, String id);

}
