package com.jidnivai.sdcian.sdcian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.Image;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.UserServiceInt;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

@Service
public class UserService implements UserServiceInt {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = getUser(id);
        if (existingUser != null) {
            existingUser.setFullName(user.getFullName());
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setAddress(user.getAddress());
            existingUser.setProfilePicture(user.getProfilePicture());
            existingUser.setCoverPicture(user.getCoverPicture());
            existingUser.setAbout(user.getAbout());
            existingUser.setWebsite(user.getWebsite());
            existingUser.setFacebook(user.getFacebook());
            existingUser.setInstagram(user.getInstagram());
            existingUser.setTwitter(user.getTwitter());
            existingUser.setYoutube(user.getYoutube());
            existingUser.setGithub(user.getGithub());
            existingUser.setLinkedin(user.getLinkedin());
            existingUser.setPinterest(user.getPinterest());
            existingUser.setTiktok(user.getTiktok());
            existingUser.setSnapchat(user.getSnapchat());
            existingUser.setTelegram(user.getTelegram());
            existingUser.setWhatsapp(user.getWhatsapp());
            existingUser.setDiscord(user.getDiscord());
            existingUser.setReddit(user.getReddit());

            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Image getProfilePictureOf(Long id) {
        User user = getUser(id);
        return user.getProfilePicture();
    }

    
}
