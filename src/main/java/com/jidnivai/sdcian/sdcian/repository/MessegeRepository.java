package com.jidnivai.sdcian.sdcian.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Messege;
import com.jidnivai.sdcian.sdcian.entity.User;

@Repository
public interface MessegeRepository extends JpaRepository<Messege, Long> {

    Page<Messege> findBySenderAndReceiver(Long senderId, Long receiverId, PageRequest of);

    Page<Messege> findAllBySenderOrReceiver(User user, User user2, PageRequest of);

}
