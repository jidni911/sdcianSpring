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

import com.jidnivai.sdcian.sdcian.dto.NewMessageDto;
import com.jidnivai.sdcian.sdcian.entity.Chat;
import com.jidnivai.sdcian.sdcian.entity.Image;
import com.jidnivai.sdcian.sdcian.entity.Messege;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.MessegeServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ChatRepository;
import com.jidnivai.sdcian.sdcian.repository.ImageRepository;
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

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Page<Chat> getMesseges(Long userId, int page, int size) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        Page<Chat> chats = chatRepository.findByMembersContaining(user,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt")));
        
            return chats;
        
        // List<Chat> demoChats = new ArrayList<>();

        // Chat demoChat1 = new Chat();
        // demoChat1.setId(1L);
        // demoChat1.setName("Demo Chat 1");
        // demoChat1.setGroup(false);
        // demoChat1.setCreator(user);
        // demoChat1.setMembers(List.of(user)); // Assuming a single member
        // demoChat1.setRequestedMembers(List.of(user));
        // demoChat1.setCreatedAt(LocalDateTime.now().minusDays(2));
        // demoChat1.setUpdatedAt(LocalDateTime.now().minusHours(1));
        // demoChat1.setLastMessage("Hello!");
        // demoChat1.setLastMessageTime(LocalDateTime.now().minusHours(1));

        // Chat demoChat2 = new Chat();
        // demoChat2.setId(2L);
        // demoChat2.setName("Demo Chat 2");
        // demoChat2.setGroup(true);
        // demoChat2.setCreator(user);
        // demoChat2.setMembers(List.of(user));
        // demoChat2.setRequestedMembers(List.of(user));
        // demoChat2.setCreatedAt(LocalDateTime.now().minusDays(5));
        // demoChat2.setUpdatedAt(LocalDateTime.now().minusMinutes(30));
        // demoChat2.setLastMessage("Hi!");
        // demoChat2.setLastMessageTime(LocalDateTime.now().minusMinutes(30));

        // demoChats.add(demoChat1);
        // demoChats.add(demoChat2);

        // return new PageImpl<>(demoChats, PageRequest.of(page, size), demoChats.size());
    }

    @Override
    public Messege getMessege(Long id) {
        return messegeRepository.findById(id).orElse(null);
    }

    @Override
    public Messege createMessege(NewMessageDto newMessageDto, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        Chat chat = chatRepository.findById(newMessageDto.getChatId()).orElse(null);
        if (chat == null) {
            return null;
        }
        if (!chat.getMembers().contains(user)) {
            return null;
        }
        Messege messege = new Messege();
        messege.setSender(user);
        messege.setMessage(newMessageDto.getMessege());
        messege.setChat(chat);
        chat.setLastMessage(newMessageDto.getMessege());
        chat.setLastMessageTime(LocalDateTime.now());
        chat.setMessegeCount(chat.getMessegeCount() + 1);
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

    @Override
    public Page<Messege> getMessagesInChat(Long chatId, Long userId, int page, int size) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        Chat chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) {
            return null;
        }
        if (!chat.getMembers().contains(user)) {
            return null;
        }

        Page<Messege> messeges = messegeRepository.findByChat(chat,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt")));

        return messeges;
    }

    @Override
    public Page<User> getSuggestions(String query, Long id, int page, int size) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        Page<User> users = userRepository.findByFullNameContainingOrUsernameContainingOrEmailContaining(query, query,
                query, PageRequest.of(page, size));
        return users;

    }

    @Override
    public Chat newChat(String name, List<Long> ids,Long imageId, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        List<User> users = userRepository.findAllById(ids);
        Chat chat = new Chat();
        chat.setName(name);
        chat.setMembers(users);
        chat.getMembers().add(user);
        chat.setRequestedMembers(new ArrayList<>());
        chat.setCreator(user);
        chat.setLastMessage("");
        chat.setLastMessageTime(LocalDateTime.now());
        if (imageId != null) {
            Image image = imageRepository.findById(imageId).orElse(null);
            if (image != null) {
                chat.setGroupImage(image);
            }
            
        }else{
            chat.setGroupImage(user.getProfilePicture());
        }
        return chatRepository.save(chat);
    }
}
