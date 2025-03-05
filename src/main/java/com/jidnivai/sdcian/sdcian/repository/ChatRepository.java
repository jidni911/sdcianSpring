package com.jidnivai.sdcian.sdcian.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.sdcian.sdcian.entity.Chat;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Page<Chat> findByMembersContaining(User user, PageRequest of);
    
}
