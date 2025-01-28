package com.jidnivai.sdcian.sdcian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.Bug;
import com.jidnivai.sdcian.sdcian.interfaces.BugServiceInt;
import com.jidnivai.sdcian.sdcian.repository.BugRepository;

@Service
public class BugService implements BugServiceInt {

    @Autowired
    private BugRepository bugRepository;

    @Override
    public Page<Bug> getAllBugs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bugRepository.findAll(pageable);
    }

    @Override
    public Bug getBugById(Long id) {
        return bugRepository.findById(id).orElse(null);
    }

    @Override
    public Bug addBug(Bug bug) {
        return bugRepository.save(bug);
    }

    @Override
    public Bug updateBug(Bug bug, Long id) {
        return bugRepository.save(bug);
    }

    @Override
    public void deleteBug(Long id) {
        bugRepository.deleteById(id);
    }


}
