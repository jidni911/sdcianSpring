package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.dto.TempFileMetadata;
import com.jidnivai.sdcian.sdcian.interfaces.TempFileServiceInterface;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tempfile")
@CrossOrigin(origins = "*")
public class TempFileController {

    @Autowired
    private TempFileServiceInterface tempService;

    @GetMapping
    public List<TempFileMetadata> getAllFiles() throws IOException {
        return tempService.getAllFiles();
    }

    @PostMapping
    public Boolean uploadFile(MultipartFile file) {
        return tempService.saveFile(file);
    }

    @DeleteMapping("/{name}")
    public Boolean deleteFile(@PathVariable String name) {
        return tempService.deleteFile(name);
    }

    @PostMapping("/mark-important/{name}")
    public Boolean markImportant(@PathVariable String name) {
        return tempService.markImportant(name, true);
        // return ResponseEntity.ok().body("Marked Important");
    }

    @PostMapping("/mark-unimportant/{name}")
    public Boolean markUnimportant(@PathVariable String name) {
        return tempService.markImportant(name, false);
    }

    @PostMapping("/check-password")
    public Map<String, Boolean> checkPassword(@RequestBody Map<String, String> body) {
        String password = body.get("password");
        boolean verified = tempService.checkPassword(password);
        return Map.of("verified", verified);
    }
}
