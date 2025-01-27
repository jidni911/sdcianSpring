package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Percel;

public interface PercelServiceInt {

    Percel getPercel(Long id);

    Page<Percel> getPercels();

    Percel createPercel(Percel percel);

    Percel updatePercel(Long id, Percel percel);

    void deletePercel(Long id);

    Page<Percel> search(String name, int page, int size);

    Page<Percel> getPercelsBySender(Long id, int page, int size);

    Page<Percel> getPercelsByRider(Long id, int page, int size);

}
