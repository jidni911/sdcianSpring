package com.jidnivai.sdcian.sdcian.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.entity.Image;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.UserServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ImageRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@Service
public class UserService implements UserServiceInt {

    @Autowired
    private UserRepository userRepository;

    @Value("${sdcian.app.images-upload-path}")
    String imageFolder;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public User getUser(Long id, Long userId) {
        User user = userRepository.findById(id).orElse(null);
        if (user.getId() != userId) {
            user.setPassword(null);
        }
        return user;
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
        User existingUser = getUser(id, user.getId());
        if (existingUser != null) {
            existingUser.setFullName(user.getFullName());
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            // existingUser.setPassword(user.getPassword());
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
        User user = getUser(id, id);
        return user.getProfilePicture();
    }

    @Override
    public Image changeProfilePicture(UserDetailsImpl userImpl, MultipartFile image) {
        User user = getUser(userImpl.getId(), userImpl.getId());
        String userFolder = imageFolder + user.getUsername() + "/";
        String fileName = user.getUsername() + "_" + System.currentTimeMillis() + "_" + image.getOriginalFilename();

        try {
            // Ensure directory exists
            Path userPath = Paths.get(userFolder);
            if (!Files.exists(userPath)) {
                Files.createDirectories(userPath);
            }

            // Write file
            Path filePath = userPath.resolve(fileName);
            Files.write(filePath, image.getBytes());

            // Save to database
            Image img = new Image();
            img.setUser(user);
            img.setName(fileName);
            img.setPath(userFolder + fileName);
            img.setUrl("/images/" + user.getUsername() + "/" + fileName);
            img = imageRepository.save(img);
            user.setProfilePicture(img);
            userRepository.save(user);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Image changeCoverPicture(UserDetailsImpl userImpl, MultipartFile image) {
        User user = getUser(userImpl.getId(), userImpl.getId());
        String userFolder = imageFolder + user.getUsername() + "/";
        String fileName = user.getUsername() + "_" + System.currentTimeMillis() + "_" + image.getOriginalFilename();

        try {
            // Ensure directory exists
            Path userPath = Paths.get(userFolder);
            if (!Files.exists(userPath)) {
                Files.createDirectories(userPath);
            }

            // Write file
            Path filePath = userPath.resolve(fileName);
            Files.write(filePath, image.getBytes());

            // Save to database
            Image img = new Image();
            img.setUser(user);
            img.setName(fileName);
            img.setPath(userFolder + fileName);
            img.setUrl("/images/" + user.getUsername() + "/" + fileName);
            img = imageRepository.save(img);
            user.setCoverPicture(img);
            userRepository.save(user);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
