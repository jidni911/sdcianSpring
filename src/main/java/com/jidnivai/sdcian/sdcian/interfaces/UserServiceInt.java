package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.Image;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface UserServiceInt {
    
    public User getUser(Long id);

    public List<User> getUsers();

    public User createUser(User user);

    public User updateUser(Long id, User user);

    public void deleteUser(Long id);

    public Image getProfilePictureOf(Long id);

}
