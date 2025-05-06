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

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.interfaces.UserServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceInt userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return userService.getUser(id, user.getId());

        } catch (Exception e) {
            System.out.println("UserController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping
    public List<User> getUsers() {
        try {
            return userService.getUsers();

        } catch (Exception e) {
            System.out.println("UserController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        try {

            return userService.createUser(user);
        } catch (Exception e) {
            System.out.println("UserController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            return userService.updateUser(id, user);

        } catch (Exception e) {
            System.out.println("UserController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);

        } catch (Exception e) {
            System.out.println("UserController: " + e.getMessage());
            // return null;
        }
    }

    @GetMapping("/getProfilePictureOf/{id}")
    public Image getProfilePictureOf(@PathVariable Long id) {
        try {
            return userService.getProfilePictureOf(id);

        } catch (Exception e) {
            System.out.println("UserController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/changeProfilePicture")
    public Image changeProfilePicture(@RequestParam MultipartFile image,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return userService.changeProfilePicture(user, image);

        } catch (Exception e) {
            System.out.println("UserController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/changeCoverPicture")
    public Image changeCoverPicture(@RequestParam MultipartFile image, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return userService.changeCoverPicture(user, image);

        } catch (Exception e) {
            System.out.println("UserController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/me")
    // @PreAuthorize("permitAll()")
    public User getMySelf(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            if (user == null)
                return null;
            return userService.getUser(user.getId(), user.getId());
        } catch (Exception e) {
            System.out.println("UserController: " + e.getMessage());
            return null;
        }
    }

}
