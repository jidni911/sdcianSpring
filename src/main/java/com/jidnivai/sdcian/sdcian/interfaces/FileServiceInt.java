package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.dto.ImageUploadResponse;
import com.jidnivai.sdcian.sdcian.dto.VideoUploadResponse;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

public interface FileServiceInt {

    ImageUploadResponse uploadImage(MultipartFile image, UserDetailsImpl user);

    VideoUploadResponse uploadVideo(MultipartFile video, UserDetailsImpl user);

    Image getImage(Long id);

}
