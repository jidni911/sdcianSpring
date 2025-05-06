package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.User;

public interface AuthServiceInt {

    public User login(String username, String password);

    public String forgotPassword(String email);

    public String signup(User user);

    public Boolean checkUsernameAvailability(String username);

    public Boolean checkEmailAvailability(String email);

    public List<String> getAllUserNames();

    public User getUserByIdentifier(String identifier);

    
}
