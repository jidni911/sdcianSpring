package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.entity.Bug;
import com.jidnivai.sdcian.sdcian.interfaces.BugServiceInt;

@RestController
@RequestMapping("/bugs")
public class BugController {

    @Autowired
    private BugServiceInt bugServiceInt;
    

    
    @GetMapping
    public Page<Bug> getAllBugs(
        @RequestParam(required = false,defaultValue = "0") int page,
        @RequestParam(required = false,defaultValue = "10") int size
    ){
        return bugServiceInt.getAllBugs(page,size);
    }

    @GetMapping("/{id}")
    public Bug getBugById(@PathVariable Long id){
        return bugServiceInt.getBugById(id);
    }

    @PostMapping
    public Bug addBug(@RequestBody Bug bug){
        return bugServiceInt.addBug(bug);
    }

    @PutMapping("/{id}")
    public Bug updateBug(@RequestBody Bug bug, @PathVariable Long id){
        return bugServiceInt.updateBug(bug, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBug(@PathVariable Long id){
        bugServiceInt.deleteBug(id);
    }

    
}



