package com.jidnivai.sdcian.sdcian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.entity.Rating;
import com.jidnivai.sdcian.sdcian.interfaces.RatingServiceInt;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingServiceInt ratingService;

    @GetMapping("/{id}")
    public Rating getRating(@PathVariable Long id) {
        return ratingService.getRating(id);
    }

    @GetMapping
    public List<Rating> getRatings() {
        return ratingService.getRatings();
    }

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.createRating(rating);
    }

    @PutMapping("/{id}")
    public Rating updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        return ratingService.updateRating(id, rating);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }

    @GetMapping("/product/{id}")
    public List<Rating> getRatingsByProduct(@PathVariable Long id) {
        return ratingService.getRatingsByProduct(id);
    }

    @GetMapping("/user/{userId}")
    public List<Rating> getRatingsByUser(@PathVariable Long userId) {
        return ratingService.getRatingsByUser(userId);
    }

}
