package com.jidnivai.sdcian.sdcian.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.entity.Image;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.intro.Home;
import com.jidnivai.sdcian.sdcian.entity.intro.Special;
import com.jidnivai.sdcian.sdcian.repository.HomeRepository;
import com.jidnivai.sdcian.sdcian.repository.ImageRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

@Service
public class HomeService {
    @Autowired
    HomeRepository homeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;

    @Value("${sdcian.app.home-upload-path}")
    String homeImageFolder;
    
    
    
    
    public Home get(){
        List<Home> homes = homeRepository.findAll();
        if (homes.size() > 0) {
            return homes.get(0);
        } else {
            Home home = new Home();
            home.setWelcomeText("Hello SDCians!");
            return homeRepository.save(home);
        }
    }


   


    public Home update(String welcomeText, MultipartFile welcomeImage, Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) return null;
        Image image = uploadImage(welcomeImage, user);
        if (image == null) {
            return null;
        }
        Home homeToUpdate = get();
        homeToUpdate.setWelcomeText(welcomeText);
        homeToUpdate.setWelcomeImage(image);
        return homeRepository.save(homeToUpdate);
    }


    private Image uploadImage(MultipartFile image, User user) {
        String userFolder = homeImageFolder + user.getUsername() + "/";
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
            img.setUrl("/homeImages/" + user.getUsername() + "/" + fileName);
            img = imageRepository.save(img);

            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }





    public Home addSpecial(Long id, String title, String description, MultipartFile[] images, Long id2) {
        User user = userRepository.findById(id2).orElse(null);
        if(user == null) return null;
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) return null;
        Home homeToUpdate = get();
        List<Special> specials = homeToUpdate.getSpecials();
        Special special ;
        if(id == null) {
            special = new Special();
        } else {
            special = homeToUpdate.getSpecials().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
            if (special == null) {
                special = new Special();
            }
        }
        special.setTitle(title);
        special.setDescription(description);
        special.setHome(homeToUpdate);
        // special = homeRepository.save(special);

        //TODO delete old images
        special.setImages(new ArrayList<>());
        for (MultipartFile image : images) {
            Image img = uploadImage(image, user);
            if (img != null) {
                special.getImages().add(img);
            }
        }
        if(id==null) specials.add(special);
        return homeRepository.save(homeToUpdate);
    }
    
}
