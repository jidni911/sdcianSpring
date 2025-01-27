package com.jidnivai.sdcian.sdcian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.AuthServiceInt;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

@Service
public class AuthService implements AuthServiceInt {

    //TODO rework
    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public String forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Implement password reset logic here
            return "Password reset instructions sent";
        }
        return "Email not found";
    }

    @Override
    public String signup(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            userRepository.save(user);
            return "Signup successful";
        }
        return "Username already exists";
    }



}
