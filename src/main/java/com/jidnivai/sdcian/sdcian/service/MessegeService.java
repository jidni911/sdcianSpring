package com.jidnivai.sdcian.sdcian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.Messege;
import com.jidnivai.sdcian.sdcian.interfaces.MessegeServiceInt;
import com.jidnivai.sdcian.sdcian.repository.MessegeRepository;

@Service
public class MessegeService implements MessegeServiceInt {

    @Autowired
    private MessegeRepository messegeRepository;

    @Override
    public Page<Messege> getMesseges() {
        return messegeRepository.findAll(PageRequest.of(0, 10));
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

