package com.jidnivai.sdcian.sdcian.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.Chat;
import com.jidnivai.sdcian.sdcian.entity.Messege;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.MessegeServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ChatRepository;
import com.jidnivai.sdcian.sdcian.repository.MessegeRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

@Service
public class MessegeService implements MessegeServiceInt {

    @Autowired
    private MessegeRepository messegeRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<Chat> getMesseges(Long userId, int page, int size) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        Page<Chat> chats = chatRepository.findByMembersContaining(user,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt")));
        if (chats.getContent().size() > 0) {

            return chats;
        }
        List<Chat> demoChats = new ArrayList<>();

        Chat demoChat1 = new Chat();
        demoChat1.setId(1L);
        demoChat1.setName("Demo Chat 1");
        demoChat1.setGroup(false);
        demoChat1.setCreator(user);
        demoChat1.setMembers(List.of(user)); // Assuming a single member
        demoChat1.setRequestedMembers(List.of(user));
        demoChat1.setCreatedAt(LocalDateTime.now().minusDays(2));
        demoChat1.setUpdatedAt(LocalDateTime.now().minusHours(1));
        
        Chat demoChat2 = new Chat();
        demoChat2.setId(2L);
        demoChat2.setName("Demo Chat 2");
        demoChat2.setGroup(true);
        demoChat2.setCreator(user);
        demoChat2.setMembers(List.of(user));
        demoChat2.setRequestedMembers(List.of(user));
        demoChat2.setCreatedAt(LocalDateTime.now().minusDays(5));
        demoChat2.setUpdatedAt(LocalDateTime.now().minusMinutes(30));

        demoChats.add(demoChat1);
        demoChats.add(demoChat2);

        return new PageImpl<>(demoChats, PageRequest.of(page, size), demoChats.size());
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
        // return messegeRepository.findBySenderAndReceiver(senderId, receiverId,
        // PageRequest.of(0, 10));
        return null;// TODO
    }
}
