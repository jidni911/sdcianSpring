package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Percel;
import com.jidnivai.sdcian.sdcian.enums.PercelStatus;

public interface PercelServiceInt {

    Percel getPercel(Long id);

    Page<Percel> getPercels(int page, int size);

    Percel createPercel(Percel percel);

    Percel updatePercel(Long id, Percel percel);

    void deletePercel(Long id);

    Page<Percel> search(String name, int page, int size);

    Page<Percel> getPercelsBySender(Long id, int page, int size);

    Page<Percel> getPercelsByRider(Long id, int page, int size);

    Page<Percel> getPercelsByStatus(PercelStatus status, int page, int size);

    Page<Percel> getPercelsByStatusAndSender(PercelStatus status, Long id, int page, int size);

    Page<Percel> getPercelsByStatusAndRider(PercelStatus status, Long id, int page, int size);

    Page<Percel> getPercelsByStatusAndSenderAndRider(PercelStatus status, Long senderId, Long riderId, int page, int size);

    Page<Percel> getPercelsBySenderAndRider(Long senderId, Long riderId, int page, int size);

    
    long count();
    
    long countBySenderId(Long id);
    
    long countByRiderId(Long id);
    
    long countByNameContaining(String name);
    
    long countByStatus(PercelStatus status);
    
    long countBySenderIdAndStatus(Long id, PercelStatus status);
    
    long countByRiderIdAndStatus(Long id, PercelStatus status);

}
