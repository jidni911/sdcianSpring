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

import com.jidnivai.sdcian.sdcian.dto.ImageUploadResponse;
import com.jidnivai.sdcian.sdcian.dto.VideoUploadResponse;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.entity.storage.Video;
import com.jidnivai.sdcian.sdcian.interfaces.FileServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ImageRepository;
import com.jidnivai.sdcian.sdcian.repository.VideoRepository;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@Service
public class FileService implements FileServiceInt {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    UserService userService;


    
// sdcian.app.images-upload-path=src/main/resources/static/images/

// sdcian.app.videos-upload-path=src/main/resources/static/videos/
    @Value("${sdcian.app.images-upload-path}")
    String imageFolder;

    @Value("${sdcian.app.videos-upload-path}")
    String videoFolder;

    @Override
    public ImageUploadResponse uploadImage(MultipartFile image, UserDetailsImpl user) {
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
            img.setUser(userService.getUser(user.getId(), user.getId()));
            img.setName(fileName);
            img.setPath(userFolder + fileName);
            img.setUrl("/images/" + user.getUsername() + "/" + fileName);
            img = imageRepository.save(img);

            // Generate the URL (adjust the base URL as needed)
            String imageUrl = "/images/" + user.getUsername() + "/" + fileName;

            return new ImageUploadResponse(img.getId(), imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VideoUploadResponse uploadVideo(MultipartFile video, UserDetailsImpl user) {
        String userFolder = videoFolder + user.getUsername() + "/";
        String fileName = user.getUsername() + "_" + System.currentTimeMillis() + "_" + video.getOriginalFilename();

        try {
            // Ensure directory exists
            Path userPath = Paths.get(userFolder);
            if (!Files.exists(userPath)) {
                Files.createDirectories(userPath);
            }

            // Write file
            Path filePath = userPath.resolve(fileName);
            Files.write(filePath, video.getBytes());

            // Save to database
            Video vid = new Video();
            vid.setUser(userService.getUser(user.getId(), user.getId()));
            vid.setName(fileName);
            vid.setPath(userFolder + fileName);
            vid.setUrl("/videos/" + user.getUsername() + "/" + fileName);
            vid = videoRepository.save(vid);

            // Generate the URL (adjust the base URL as needed)
            String videoUrl = "/videos/" + user.getUsername() + "/" + fileName;

            return new VideoUploadResponse(vid.getId(), videoUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Image> getImages(List<Long> galleryImagesId) {
        return imageRepository.findAllById(galleryImagesId);
    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
