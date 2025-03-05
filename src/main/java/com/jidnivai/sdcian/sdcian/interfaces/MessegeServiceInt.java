package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Chat;
import com.jidnivai.sdcian.sdcian.entity.Messege;

public interface MessegeServiceInt {

    Page<Chat> getMesseges(Long userId, int page, int size);

    Messege getMessege(Long id);

    Messege createMessege(Messege messege);

    Messege updateMessege(Long id, Messege messege);

    void deleteMessege(Long id);

    Page<Messege> getMessegesBetweenSenderAndReceiver(Long senderId, Long receiverId);

}
