package com.jidnivai.sdcian.sdcian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.merchandise.JerseyOrder;

public interface JerseyOrderRepository extends JpaRepository<JerseyOrder, Long> {

    List<JerseyOrder> findAllByUserAndPending(User user, boolean b);

    List<JerseyOrder> findAllByUserAndPendingOrderByCreatedAtDesc(User user, boolean b);
    
}
