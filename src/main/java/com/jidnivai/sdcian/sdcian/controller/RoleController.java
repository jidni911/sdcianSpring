package com.jidnivai.sdcian.sdcian.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.entity.Role;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.RoleServiceInt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceInt roleService;

    @GetMapping("/{roleName}")
    public List<User> getUsersByRoleName(@PathVariable String roleName) {
        return roleService.getUsersByRoleName(roleName);
    }

    @GetMapping("/roleOf/{id}")
    public Set<Role> getRole(@PathVariable String id) {
        return roleService.getRolesOfUser(id);
    }

    @PostMapping("/assign/{roleName}/to/{id}")
    public Set<Role> assignRole(@PathVariable String roleName, @PathVariable String id) {
        return roleService.assignRole(roleName, id);
    }

    @PostMapping("/remove/{roleName}/from/{id}")
    public Set<Role> removeRole(@PathVariable String roleName, @PathVariable String id) {
        return roleService.removeRole(roleName, id);
    }
    
    
}
