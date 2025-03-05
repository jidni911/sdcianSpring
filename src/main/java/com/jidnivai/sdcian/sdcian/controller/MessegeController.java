package com.jidnivai.sdcian.sdcian.controller;

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

import com.jidnivai.sdcian.sdcian.entity.Chat;
import com.jidnivai.sdcian.sdcian.entity.Messege;
import com.jidnivai.sdcian.sdcian.interfaces.MessegeServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/messege")
public class MessegeController {

    @Autowired
    MessegeServiceInt messegeServiceInt;

    @GetMapping
    public Page<Chat> getMesseges(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @AuthenticationPrincipal UserDetailsImpl user) {
                System.out.println(user);
        return messegeServiceInt.getMesseges(user.getId(),page,size);
    }

    @GetMapping("/{id}")
    public Messege getMessege(@PathVariable Long id) {
        return messegeServiceInt.getMessege(id);
    }

    @PostMapping
    public Messege createMessege(@RequestBody Messege messege) {
        return messegeServiceInt.createMessege(messege);
    }

    @PutMapping("/{id}")
    public Messege updateMessege(@PathVariable Long id, @RequestBody Messege messege) {
        return messegeServiceInt.updateMessege(id, messege);
    }

    @DeleteMapping("/{id}")
    public void deleteMessege(@PathVariable Long id) {
        messegeServiceInt.deleteMessege(id);
    }

    @GetMapping("/between/{senderId}/{receiverId}")
    public Page<Messege> getMessegesBetweenSenderAndReceiver(
            @PathVariable Long senderId,
            @PathVariable Long receiverId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return messegeServiceInt.getMessegesBetweenSenderAndReceiver(senderId, receiverId);
    }

}
