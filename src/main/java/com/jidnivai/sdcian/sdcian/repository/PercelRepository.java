package com.jidnivai.sdcian.sdcian.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Percel;
import com.jidnivai.sdcian.sdcian.enums.PercelStatus;

@Repository
public interface PercelRepository extends JpaRepository<Percel, Long> {

    long countBySenderId(Long id);

    long countByRiderId(Long id);

    long countByNameContaining(String name);

    long countByStatus(PercelStatus status);

    long countBySenderIdAndStatus(Long id, PercelStatus status);

    long countByRiderIdAndStatus(Long id, PercelStatus status);

    Page<Percel> findByNameContaining(String name, PageRequest pageRequest);

    Page<Percel> findBySenderId(Long id, PageRequest pageRequest);

    Page<Percel> findByRiderId(Long id, PageRequest pageRequest);

    Page<Percel> findByStatus(PercelStatus status, PageRequest pageRequest);

    Page<Percel> findByStatusAndSenderId(PercelStatus status, Long id, PageRequest pageRequest);

    Page<Percel> findByStatusAndRiderId(PercelStatus status, Long id, PageRequest pageRequest);

    Page<Percel> findByStatusAndSenderIdAndRiderId(PercelStatus status, Long senderId, Long riderId,
            PageRequest pageRequest);

    Page<Percel> findBySenderIdAndRiderId(Long senderId, Long riderId, PageRequest pageRequest);

}
