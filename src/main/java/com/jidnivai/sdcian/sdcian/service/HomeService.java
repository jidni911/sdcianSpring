package com.jidnivai.sdcian.sdcian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.intro.Home;
import com.jidnivai.sdcian.sdcian.repository.HomeRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

@Service
public class HomeService {
    @Autowired
    HomeRepository homeRepository;
    @Autowired
    UserRepository userRepository;
    
    
    
    
    public Home get(){
        List<Home> homes = homeRepository.findAll();
        if (homes.size() > 0) {
            return homes.get(0);
        } else {
            Home home = new Home();
            home.setWelcomeText("Hello SDCians!");
            return homeRepository.save(home);
        }
    }


    public Home update(Home home, Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) return null;
        Home homeToUpdate = get();
        homeToUpdate.setWelcomeText(home.getWelcomeText());
        return homeRepository.save(homeToUpdate);
    }
    
}
