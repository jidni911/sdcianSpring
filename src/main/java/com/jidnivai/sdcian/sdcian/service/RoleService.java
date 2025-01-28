package com.jidnivai.sdcian.sdcian.service;

import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.RoleServiceInt;
import com.jidnivai.sdcian.sdcian.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService implements RoleServiceInt {

    @Autowired
    private  RoleRepository roleRepository;


    @Override
    public Page<User> getUsersByRoleName(String roleName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roleRepository.findByName(roleName, pageable);
    }

    @Override
    public Set<Role> getRolesOfUser(String id) {
        return roleRepository.findByUserId(id);
    }

    @Override
    public void assignRole(String roleName, Long id) {
        Role role = roleRepository.findByRoleName(roleName);
        User user = new User();
        user.setId(id);
        role.getUsers().add(user);
        roleRepository.save(role);
    }

    @Override
    public void removeRole(String roleName, Long id) {
        Role role = roleRepository.findByRoleName(roleName);
        role.getUsers().removeIf(user -> user.getId().equals(id));
        roleRepository.save(role).getUsers();
    }
}

