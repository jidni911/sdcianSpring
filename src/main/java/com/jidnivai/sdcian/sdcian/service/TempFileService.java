package com.jidnivai.sdcian.sdcian.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.dto.TempFileMetadata;
import com.jidnivai.sdcian.sdcian.interfaces.TempFileServiceInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class TempFileService implements TempFileServiceInterface {

    @Value("${sdcian.app.tempFile-upload-path}")
    private String uploadDir;

    @Value("${sdcian.app.tempFile-password}")
    private String configuredPassword;

    private final Map<String, Boolean> importantMap = new HashMap<>();

    @Override
    public List<TempFileMetadata> getAllFiles() throws IOException {
        File folder = new File(uploadDir);
        if (!folder.exists()) folder.mkdirs();

        File[] files = folder.listFiles();
        List<TempFileMetadata> metadataList = new ArrayList<>();

        if (files != null) {
            for (File f : files) {
                metadataList.add(new TempFileMetadata(
                    f.getName(),
                    f.length(),
                    Files.probeContentType(f.toPath()),
                    "/uploads/tempFile/" + f.getName(),
                    importantMap.getOrDefault(f.getName(), false),
                    new Date(f.lastModified())
                ));
            }
        }
        return metadataList;
    }

    @Override
    public Boolean saveFile(MultipartFile file) {
        try {
            Path destinationPath = Paths.get(uploadDir, file.getOriginalFilename());
            Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.out.println("TempFileService saveFile: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteFile(String name) {
        if(name.contains(File.separator)) return false;
        File f = new File(uploadDir + File.separator + name);
        importantMap.remove(name);
        if (f.exists()) {
            f.delete();
            return true;
        }
        return false;
    }

    @Override
    public Boolean markImportant(String name, boolean important) {
        importantMap.put(name, important);
        return true;
    }

    @Override
    public boolean checkPassword(String password) {
        return configuredPassword.equals(password);
    }
}
