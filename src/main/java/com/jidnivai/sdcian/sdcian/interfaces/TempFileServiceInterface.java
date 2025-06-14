package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.dto.TempFileMetadata;

import java.io.IOException;
import java.util.List;

public interface TempFileServiceInterface {
    List<TempFileMetadata> getAllFiles() throws IOException;
    Boolean saveFile(MultipartFile file);
    Boolean deleteFile(String name);
    Boolean markImportant(String name, boolean important);
    boolean checkPassword(String password);
}