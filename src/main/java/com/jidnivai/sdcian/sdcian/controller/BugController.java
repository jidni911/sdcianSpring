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
@RequestMapping("/api/bugs")
public class BugController {

    @Autowired
    private BugServiceInt bugServiceInt;

    @GetMapping
    public Page<Bug> getAllBugs(
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "10") int size
    ) {
        try {
            return bugServiceInt.getAllBugs(page, size);
        } catch (Exception e) {
            System.out.println("BugController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Bug getBugById(@PathVariable Long id) {
        try {
            return bugServiceInt.getBugById(id);
        } catch (Exception e) {
            System.out.println("BugController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Bug addBug(@RequestBody Bug bug) {
        try {
            return bugServiceInt.addBug(bug);
        } catch (Exception e) {
            System.out.println("BugController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public Bug updateBug(@RequestBody Bug bug, @PathVariable Long id) {
        try {
            return bugServiceInt.updateBug(bug, id);
        } catch (Exception e) {
            System.out.println("BugController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBug(@PathVariable Long id) {
        try {
            bugServiceInt.deleteBug(id);
        } catch (Exception e) {
            System.out.println("BugController: " + e.getMessage());
        }
    }

}

