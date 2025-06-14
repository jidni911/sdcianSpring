package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.entity.merchandise.Jersey;
import com.jidnivai.sdcian.sdcian.entity.merchandise.JerseyOrder;
import com.jidnivai.sdcian.sdcian.interfaces.JerseyServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/jersey")
public class JerseyController {
    
    @Autowired
    JerseyServiceInt jerseyService;

    @GetMapping()
    public List<Jersey> getJerseys() {
        try {
            return jerseyService.getJerseys();
        } catch (Exception e) {
            System.out.println("JerseyController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Jersey getJersey(@PathVariable Long id) {
        try {
            return jerseyService.getJersey(id);
        } catch (Exception e) {
            System.out.println("JerseyController: " + e.getMessage());
            return null;
        }
    }

    
    @PostMapping()
    public Jersey addJersey(
        @RequestBody Jersey jersey,
        @AuthenticationPrincipal UserDetailsImpl user
    ) {
        try{
            return jerseyService.addJersey(jersey, user.getUser());
        }catch(Exception e){
            System.out.println("JerseyController: " + e.getMessage());
            return null;
        }
    }





    @PostMapping("/order")
    public JerseyOrder placeOrder(
        @RequestBody JerseyOrder jerseyOrder,
        @AuthenticationPrincipal UserDetailsImpl user
    ) {
        try{
            return jerseyService.placeOrder(jerseyOrder, user.getUser());
        }catch(Exception e){
            System.out.println("JerseyController: " + e.getMessage());
            return null;
        }
    }


    @GetMapping("/order")
    public List<JerseyOrder> getJerseyOrders(
        @AuthenticationPrincipal UserDetailsImpl user
    ) {
        try {
            return jerseyService.getJerseyOrders(user.getUser());
        } catch (Exception e) {
            System.out.println("JerseyController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/payment")
    public JerseyOrder makePayment(@RequestBody JerseyOrder entity, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return jerseyService.makePayment(entity,user.getUser());
        } catch (Exception e) {
            System.out.println("JerseyController: " + e.getMessage());
            return null;
        }
        
        // return entity;
    }
    



    
}
