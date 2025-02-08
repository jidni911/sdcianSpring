package com.jidnivai.sdcian.sdcian.service;

import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.RoleServiceInt;
import com.jidnivai.sdcian.sdcian.repository.RoleRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService implements RoleServiceInt {

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getUsersByRoleName(String roleName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roleRepository.findByName(roleName, pageable);
    }

    @Override
    public Set<Role> getRolesOfUser(Long id) {
        // System.out.println(roleRepository.findByUsersId(id));
        return roleRepository.findByUsersId(id);
    }

    @Override
    public Set<Role> assignRole(String roleName, Long id) {
        Optional<Role> optionalRole = roleRepository.findByName(roleName);
        Role role;
        if (optionalRole.isPresent()) {
            role = optionalRole.get();
        } else {
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        // this.users.remove(user);
        // user.getRoles().remove(this);
        role.getUsers().add(user);
        user.getRoles().add(role);
        roleRepository.save(role);
        return userRepository.save(user).getRoles();
        
    }

    @Override
    public Set<Role> removeRole(String roleName, Long userId) {
        Role role = roleRepository.findByName(roleName).orElseThrow();
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
    
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    
        if (role.getUsers().contains(user)) {
            role.removeUser(user);
            roleRepository.save(role); // Update the Role entity
            return userRepository.save(user).getRoles(); 
        }
    
        return user.getRoles();
    }
    

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}

