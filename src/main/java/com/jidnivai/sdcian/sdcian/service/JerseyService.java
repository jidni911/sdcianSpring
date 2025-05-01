package com.jidnivai.sdcian.sdcian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.merchandise.Jersey;
import com.jidnivai.sdcian.sdcian.entity.merchandise.JerseyOrder;
import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.interfaces.JerseyServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ImageRepository;
import com.jidnivai.sdcian.sdcian.repository.JerseyOrderRepository;
import com.jidnivai.sdcian.sdcian.repository.JerseyRepository;

@Service
public class JerseyService implements JerseyServiceInt {

    @Autowired
    JerseyRepository jerseyRepository;
    @Autowired
    JerseyOrderRepository jerseyOrderRepository;
    @Autowired
    ImageRepository imageRepository;

    @Override
    public Jersey addJersey(Jersey jersey, User user) {
        if (user != null && user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
            
            jersey.setCreatedBy(user);
            jersey.setUpdatedBy(user);
            List<Image> images = imageRepository.findAllById(jersey.getImageIds());
            jersey.setImages(images);
            return jerseyRepository.save(jersey);
        } else {
            return null;
            
        }
        
    }

    @Override
    public List<Jersey> getJerseys() {
        return jerseyRepository.findAll();
    }

    @Override
    public Jersey getJersey(Long id) {
        return jerseyRepository.findById(id).orElse(null);
    }

    @Override
    public JerseyOrder placeOrder(JerseyOrder jerseyOrder, User user) {
        if(user==null) return null;
        jerseyOrder.setUser(user);
        return jerseyOrderRepository.save(jerseyOrder);
    }

    @Override
    public List<JerseyOrder> getJerseyOrders(User user) {
        if(user==null) return null;
        return jerseyOrderRepository.findAllByUserAndPending(user, true);
    }
    
}
