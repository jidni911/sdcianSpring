package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.dto.NewMessageDto;
import com.jidnivai.sdcian.sdcian.entity.Chat;
import com.jidnivai.sdcian.sdcian.entity.Messege;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface MessegeServiceInt {

    Page<Chat> getMesseges(Long userId, int page, int size);

    Messege getMessege(Long id);

    Messege createMessege(NewMessageDto newMessageDto, Long userId);

    Messege updateMessege(Long id, Messege messege);

    void deleteMessege(Long id);

    Page<Messege> getMessegesBetweenSenderAndReceiver(Long senderId, Long receiverId);

    Page<Messege> getMessagesInChat(Long id, Long id2, int page, int size);

    Page<User> getSuggestions(String param, Long id, int page, int size);

    Chat newChat(String name,List<Long> ids,Long imageId, Long userId);

}
