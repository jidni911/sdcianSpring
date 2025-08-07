package com.jidnivai.sdcian.sdcian.dto.bkash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface JerseyItemRepository extends JpaRepository<JerseyItem, Long> {

    @Modifying
    @Transactional
    @Query(value = "Update jersey_item j set j.active = false where j.id = ?1", nativeQuery = true)
    void deactivateJerseyItemById(Long id);
    
}
