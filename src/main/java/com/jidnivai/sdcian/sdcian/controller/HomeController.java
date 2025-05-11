package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.sdcian.sdcian.entity.intro.Home;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;
import com.jidnivai.sdcian.sdcian.service.HomeService;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String getHomePage() {
        return "index.html";
    }

    @GetMapping("/intro/home")
    @ResponseBody
    public Home getHome() {
        try {
            return homeService.get();
            // return "Hello SDCian";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Home home = new Home();
            home.setWelcomeText("Hello SDCian");
            return home;
        }
    }

    @PostMapping("/introUpdate")
    @ResponseBody
    public Home updateHome(@RequestParam String welcomeText, @RequestParam MultipartFile welcomeImage,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            // System.out.println("Text: " + welcomeText);
            // System.out.println("Image Name: " + welcomeImage.getOriginalFilename());
            return homeService.update(welcomeText, welcomeImage, userDetails.getUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/introAddSpecial")
    @ResponseBody
    public Home AddSpecial(@RequestParam(required = false) Long id, @RequestParam String title, @RequestParam String description,
            @RequestParam MultipartFile[] images, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            // System.out.println("Text: " + welcomeText);
            // System.out.println("Image Name: " + welcomeImage.getOriginalFilename());
            // return homeService.update(welcomeText, welcomeImage, userDetails.getId());
            System.out.println("Id: " + id);
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Images: " + images.length);
            return homeService.addSpecial(id, title, description, images, userDetails.getUser());
            // return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
