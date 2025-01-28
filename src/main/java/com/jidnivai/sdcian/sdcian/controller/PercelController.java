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
@RequestMapping("/percel")
public class PercelController {

    @Autowired
    PercelServiceInt percelServiceInt;

    @GetMapping("/{id}")
    public Percel getPercel(@PathVariable Long id) {
        return percelServiceInt.getPercel(id);
    }

    @GetMapping
    public Page<Percel> getPercels(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return percelServiceInt.getPercels(page, size);
    }

    @PostMapping
    public Percel createPercel(@RequestBody Percel percel) {
        return percelServiceInt.createPercel(percel);
    }

    @PutMapping("/{id}")
    public Percel updatePercel(@PathVariable Long id, @RequestBody Percel percel) {
        return percelServiceInt.updatePercel(id, percel);
    }

    @DeleteMapping("/{id}")
    public void deletePercel(@PathVariable Long id) {
        percelServiceInt.deletePercel(id);
    }

    @GetMapping("/search/{name}")
    public Page<Percel> search(@PathVariable String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return percelServiceInt.search(name, page, size);
        
    }

    @GetMapping("/sender/{id}")
    public Page<Percel> getPercelsBySender(@PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return percelServiceInt.getPercelsBySender(id, page, size);
    }

    @GetMapping("/rider/{id}")
    public Page<Percel> getPercelsByRider(@PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return percelServiceInt.getPercelsByRider(id, page, size);
    }

}
