package com.jidnivai.sdcian.sdcian.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.dto.ImageUploadResponse;
import com.jidnivai.sdcian.sdcian.entity.Image;
import com.jidnivai.sdcian.sdcian.interfaces.FileServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ImageRepository;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@Service
public class FileService implements FileServiceInt {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    UserService userService;

    @Value("${sdcian.app.images-upload-path}")
    String folder;

    @Override
    public ImageUploadResponse uploadImage(MultipartFile image, UserDetailsImpl user) {
        String userFolder = folder + user.getUsername() + "/";
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
            img.setUser(userService.getUser(user.getId()));
            img.setName(fileName);
            img.setPath(userFolder + fileName);
            img = imageRepository.save(img);

            // Generate the URL (adjust the base URL as needed)
            String imageUrl = "/images/" + user.getUsername() + "/" + fileName;

            return new ImageUploadResponse(img.getId(), imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
