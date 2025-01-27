package com.jidnivai.sdcian.sdcian.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.entity.Bug;

@RestController
@RequestMapping("/bugs")
public class BugController {

    
    @GetMapping
    public List<Bug> getBugs() {
        return null;
    }

    @GetMapping("/{id}")
    public Bug getBug(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public Bug postBug(@RequestBody Bug bug) {
        return null;
    }

    @PutMapping("/{id}")
    public Bug putBug(@PathVariable Long id, @RequestBody Bug bug) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBug(@PathVariable Long id) {

    }

}

