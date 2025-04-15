package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.dto.ImageUploadResponse;
import com.jidnivai.sdcian.sdcian.dto.VideoUploadResponse;
import com.jidnivai.sdcian.sdcian.interfaces.FileServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileServiceInt fileServiceInt;

    @PostMapping("/uploadImage")
    public ImageUploadResponse uploadFile(@RequestParam MultipartFile image,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return fileServiceInt.uploadImage(image, user);
        } catch (Exception e) {
            System.out.println("FileController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/uploadVideo")//TODO update 
    public VideoUploadResponse uploadVideo(@RequestParam MultipartFile video,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return fileServiceInt.uploadVideo(video, user);
        } catch (Exception e) {
            System.out.println("FileController: " + e.getMessage());
            return null;
        }
    }

   

}

