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

import com.jidnivai.sdcian.sdcian.entity.Percel;
import com.jidnivai.sdcian.sdcian.interfaces.PercelServiceInt;

@RestController
@RequestMapping("/api/percel")
public class PercelController {

    @Autowired
    PercelServiceInt percelServiceInt;

    @GetMapping("/{id}")
    public Percel getPercel(@PathVariable Long id) {
        try {
            return percelServiceInt.getPercel(id);
        } catch (Exception e) {
            System.out.println("PercelController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping
    public Page<Percel> getPercels(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return percelServiceInt.getPercels(page, size);
        } catch (Exception e) {
            System.out.println("PercelController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Percel createPercel(@RequestBody Percel percel) {
        try {
            return percelServiceInt.createPercel(percel);
        } catch (Exception e) {
            System.out.println("PercelController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public Percel updatePercel(@PathVariable Long id, @RequestBody Percel percel) {
        try {
            return percelServiceInt.updatePercel(id, percel);
        } catch (Exception e) {
            System.out.println("PercelController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deletePercel(@PathVariable Long id) {
        try {
            percelServiceInt.deletePercel(id);
        } catch (Exception e) {
            System.out.println("PercelController: " + e.getMessage());
        }
    }

    @GetMapping("/search/{name}")
    public Page<Percel> search(@PathVariable String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return percelServiceInt.search(name, page, size);
        } catch (Exception e) {
            System.out.println("PercelController: " + e.getMessage());
            return null;
        }
        
    }

    @GetMapping("/sender/{id}")
    public Page<Percel> getPercelsBySender(@PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return percelServiceInt.getPercelsBySender(id, page, size);
        } catch (Exception e) {
            System.out.println("PercelController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/rider/{id}")
    public Page<Percel> getPercelsByRider(@PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return percelServiceInt.getPercelsByRider(id, page, size);
        } catch (Exception e) {
            System.out.println("PercelController: " + e.getMessage());
            return null;
        }
    }

}

