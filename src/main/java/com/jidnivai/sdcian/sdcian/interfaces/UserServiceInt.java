package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

public interface UserServiceInt {
    
    public User getUser(Long id, Long userId);

    public List<User> getUsers();

    public User createUser(User user);

    public User updateUser(Long id, User user);

    public void deleteUser(Long id);

    public Image getProfilePictureOf(Long id);

    public Image changeProfilePicture(UserDetailsImpl user, MultipartFile image);

    public Image changeCoverPicture(UserDetailsImpl user, MultipartFile image);

}
