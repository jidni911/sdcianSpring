package com.jidnivai.sdcian.sdcian.controller;

import com.jidnivai.sdcian.sdcian.dto.ImageUploadResponse;
import com.jidnivai.sdcian.sdcian.dto.VideoUploadResponse;
import com.jidnivai.sdcian.sdcian.interfaces.FileServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.*;

@RestController
public class FileController {

    @Autowired
    FileServiceInt fileServiceInt;

    @Value("${sdcian.app.images-upload-path}")
    private String imageFolder;

    @Value("${sdcian.app.home-upload-path}")
    private String homeImageFolder;

    @Value("${sdcian.app.videos-upload-path}")
    private String videoFolder;

    // ========== Upload Endpoints ========== 
    @PostMapping("/file/uploadImage")
    public ImageUploadResponse uploadFile(@RequestParam MultipartFile image,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return fileServiceInt.uploadImage(image, user);
        } catch (Exception e) {
            System.out.println("FileController uploadImage: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/file/uploadVideo")
    public VideoUploadResponse uploadVideo(@RequestParam MultipartFile video,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return fileServiceInt.uploadVideo(video, user);
        } catch (Exception e) {
            System.out.println("FileController uploadVideo: " + e.getMessage());
            return null;
        }
    }

    // ========== Serve Images ========== 
    @GetMapping("/images/**")
    public void serveImage(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(defaultValue = "false") boolean original)  {
        try {
            serveMediaFile(request, response, imageFolder, "/images/", true, original);
        } catch (Exception e) {
            System.out.println("FileController serveImage: " + e.getMessage());
            System.out.println(e.getStackTrace()[0].toString());
        }
    }

    @GetMapping("/homeImages/**")
    public void serveHomeImage(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(defaultValue = "false") boolean original) {
        try {
            serveMediaFile(request, response, homeImageFolder, "/homeImages/", true, original);
        } catch (Exception e) {
            System.out.println("FileController serveHomeImage: " + e.getMessage());
        }
    }

    @GetMapping("/videos/**")
    public void serveVideo(HttpServletRequest request, HttpServletResponse response) {
        try {
            serveMediaFile(request, response, videoFolder, "/videos/", false, true);
        } catch (Exception e) {
            System.out.println("FileController serveVideo: " + e.getMessage());
            
        } // always original for videos
    }

    // ========== Common Handler ========== 
    private void serveMediaFile(HttpServletRequest request, HttpServletResponse response, String baseFolder,
            String baseUrl, boolean isImage, boolean original) throws Exception {

        // Fixing the issue with spaces (%20)
        String requestURI = request.getRequestURI();
        String relativePath = requestURI.substring(baseUrl.length()); // path after /images/, /homeImages/, or /videos/
        
        // Decode the URL to handle spaces correctly
        relativePath = URLDecoder.decode(relativePath, "UTF-8");
        
        Path filePath = Paths.get(baseFolder, relativePath);

        if (!Files.exists(filePath)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String mimeType = Files.probeContentType(filePath);
        response.setContentType(mimeType != null ? mimeType : MediaType.APPLICATION_OCTET_STREAM_VALUE);

        try (InputStream in = Files.newInputStream(filePath)) {
            if (isImage && !original) {
                BufferedImage originalImage = ImageIO.read(in);
                int targetWidth = 800;
                int targetHeight = (originalImage.getHeight() * targetWidth) / originalImage.getWidth();

                BufferedImage resizedImage = resizeImage(originalImage, targetWidth, targetHeight);
                ImageIO.write(resizedImage, getFormatName(filePath.getFileName().toString()),
                        response.getOutputStream());
            } else {
                StreamUtils.copy(in, response.getOutputStream());
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    private String getFormatName(String filename) {
        String ext = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
        return switch (ext) {
        case "jpg", "jpeg" -> "jpg";
        case "png" -> "png";
        case "webp" -> "webp";
        default -> "jpg";
        };
    }
}
