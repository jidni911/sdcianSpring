package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
        try{
            return homeService.get();
            // return "Hello SDCian";
        }catch(Exception e){
            System.out.println(e.getMessage());
            Home home= new Home();
            home.setWelcomeText("Hello SDCian");
            return home;
        }
    }

    @PostMapping("/introUpdate")
    @ResponseBody
    public Home updateHome(@RequestBody Home home, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try{
            return homeService.update(home, userDetails.getId());
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    


    
}
