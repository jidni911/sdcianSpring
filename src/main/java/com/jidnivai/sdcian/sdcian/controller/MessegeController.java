package com.jidnivai.sdcian.sdcian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.dto.NewMessageDto;
import com.jidnivai.sdcian.sdcian.entity.Chat;
import com.jidnivai.sdcian.sdcian.entity.Messege;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.MessegeServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/messege")
public class MessegeController {

    @Autowired
    MessegeServiceInt messegeServiceInt;

    @GetMapping
    public Page<Chat> getMesseges(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size, @AuthenticationPrincipal UserDetailsImpl user) {
        if (user == null) {
            return null;
        }
        try {
            Page<Chat> messeges = messegeServiceInt.getMesseges(user.getUser(), page, size);
            return messeges;
        } catch (Exception e) {
            System.out.println("MessegeController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/chat/{id}")
    public Page<Messege> getMessagesInChat(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size, @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            Page<Messege> messeges = messegeServiceInt.getMessagesInChat(id, user.getUser(), page, size);
            return messeges;
        } catch (Exception e) {
            System.out.println("MessegeController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Messege getMessege(@PathVariable Long id) {
        try {
            return messegeServiceInt.getMessege(id);
        } catch (Exception e) {
            System.out.println("MessegeController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Messege createMessege(@RequestBody NewMessageDto newMessageDto,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return messegeServiceInt.createMessege(newMessageDto, user.getUser());
        } catch (Exception e) {
            System.out.println("MessegeController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public Messege updateMessege(@PathVariable Long id, @RequestBody Messege messege) {
        try {
            return messegeServiceInt.updateMessege(id, messege);
        } catch (Exception e) {
            System.out.println("MessegeController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMessege(@PathVariable Long id) {
        try {
            messegeServiceInt.deleteMessege(id);
        } catch (Exception e) {
            System.out.println("MessegeController: " + e.getMessage());
        }
    }

    @GetMapping("/between/{senderId}/{receiverId}")
    public Page<Messege> getMessegesBetweenSenderAndReceiver(@PathVariable Long senderId, @PathVariable Long receiverId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return messegeServiceInt.getMessegesBetweenSenderAndReceiver(senderId, receiverId);
        } catch (Exception e) {
            System.out.println("MessegeController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/newChatSuggestions/{param}")
    public Page<User> getSuggestions(@PathVariable String param,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return messegeServiceInt.getSuggestions(param.trim(), user.getUser(), page, size);
        } catch (Exception e) {
            System.out.println("MessegeController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/newChat")
    public Chat newChat(@RequestParam String name, @RequestParam List<Long> ids,
            @RequestParam(required = false) Long imageId, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return messegeServiceInt.newChat(name, ids, imageId, user.getUser());
        } catch (Exception e) {
            System.out.println("MessegeController: " + e.getMessage());
            return null;
        }
    }
}
