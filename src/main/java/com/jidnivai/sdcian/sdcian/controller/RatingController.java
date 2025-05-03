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

import com.jidnivai.sdcian.sdcian.entity.shop.Rating;
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
    public org.springframework.data.domain.Page<Rating> getRatings(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return ratingService.getRatings(page, size);

        } catch (Exception e) {
            System.out.println("RatingController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        try {
            return ratingService.createRating(rating);

        } catch (Exception e) {
            System.out.println("RatingController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public Rating updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        try {
            return ratingService.updateRating(id, rating);

        } catch (Exception e) {
            System.out.println("RatingController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        try {
            ratingService.deleteRating(id);

        } catch (Exception e) {
            System.out.println("RatingController: " + e.getMessage());
            // return null;
        }
    }

    @GetMapping("/product/{id}")
    public Page<Rating> getRatingsByProduct(@PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return ratingService.getRatingsByProduct(id, page, size);

        } catch (Exception e) {
            System.out.println("RatingController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/user/{userId}")
    public Page<Rating> getRatingsByUser(@PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return ratingService.getRatingsByUser(userId, page, size);

        } catch (Exception e) {
            System.out.println("RatingController: " + e.getMessage());
            return null;
        }
    }

}
