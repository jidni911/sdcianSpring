package com.jidnivai.sdcian.sdcian.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Page<User> getUsersByRoleName(
        @PathVariable String roleName,
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "10") int size) {
        return roleService.getUsersByRoleName(roleName, page, size);
    }

    @GetMapping("/roleOf/{id}")
    public Set<Role> getRole(@PathVariable Long id) {
        // System.out.println(id);
        return roleService.getRolesOfUser(id);
    }

    @PostMapping("/assign/{roleName}/to/{id}")
    public Set<Role> assignRole(@PathVariable String roleName, @PathVariable Long id) {
        return roleService.assignRole(roleName, id);
    }

    @PostMapping("/remove/{roleName}/from/{id}")
    public Set<Role> removeRole(@PathVariable String roleName, @PathVariable Long id) {
        return roleService.removeRole(roleName, id);
    }

    @GetMapping
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

}
