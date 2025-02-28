package com.jidnivai.sdcian.sdcian.interfaces;


import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Bug;

public interface BugServiceInt {

    Page<Bug> getAllBugs(int page, int size);

    Bug getBugById(Long id);

    Bug addBug(Bug bug);

    Bug updateBug(Bug bug, Long id);

    void deleteBug(Long id);

   

}
