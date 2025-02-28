package com.jidnivai.sdcian.sdcian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.Messege;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.MessegeServiceInt;
import com.jidnivai.sdcian.sdcian.repository.MessegeRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

@Service
public class MessegeService implements MessegeServiceInt {

    @Autowired
    private MessegeRepository messegeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<Messege> getMesseges( Long userId, int page, int size) {
        User user = userRepository.findById(userId).orElseThrow();
        return messegeRepository.findAllBySenderOrReceiver(user, user, PageRequest.of(page, size));
    }

    @Override
    public Messege getMessege(Long id) {
        return messegeRepository.findById(id).orElse(null);
    }

    @Override
    public Messege createMessege(Messege messege) {
        return messegeRepository.save(messege);
    }

    @Override
    public Messege updateMessege(Long id, Messege messege) {
        if (messegeRepository.existsById(id)) {
            messege.setId(id);
            return messegeRepository.save(messege);
        }
        return null;
    }

    @Override
    public void deleteMessege(Long id) {
        messegeRepository.deleteById(id);
    }

    @Override
    public Page<Messege> getMessegesBetweenSenderAndReceiver(Long senderId, Long receiverId) {
        return messegeRepository.findBySenderAndReceiver(senderId, receiverId, PageRequest.of(0, 10));
    }
}

