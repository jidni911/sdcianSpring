package com.jidnivai.sdcian.sdcian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.entity.Image;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.UserServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceInt userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        return userService.getUser(id, user.getId());
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/getProfilePictureOf/{id}")
    public Image getProfilePictureOf(@PathVariable Long id) {
        return userService.getProfilePictureOf(id);
    }

    @PostMapping("/changeProfilePicture")
    public Image changeProfilePicture(@RequestParam MultipartFile image,
            @AuthenticationPrincipal UserDetailsImpl user) {
        return userService.changeProfilePicture(user, image);
    }

    @PostMapping("/changeCoverPicture")
    public Image changeCoverPicture(@RequestParam MultipartFile image, @AuthenticationPrincipal UserDetailsImpl user) {
        return userService.changeCoverPicture(user, image);
    }

    @GetMapping("/me")
    public User getMySelf(@AuthenticationPrincipal UserDetailsImpl user) {
        if (user == null) return null;
        return userService.getUser(user.getId(), user.getId());
    }

}
