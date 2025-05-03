package com.jidnivai.sdcian.sdcian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.shop.Rating;
import com.jidnivai.sdcian.sdcian.interfaces.RatingServiceInt;
import com.jidnivai.sdcian.sdcian.repository.RatingRepository;


@Service
public class RatingService implements RatingServiceInt {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating getRating(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Rating> getRatings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ratingRepository.findAll(pageable);
    }

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Long id, Rating rating) {
        if (ratingRepository.existsById(id)) {
            rating.setId(id);
            return ratingRepository.save(rating);
        }
        return null;
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public Page<Rating> getRatingsByProduct(Long productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ratingRepository.findByProductId(productId, pageable);
    }

    @Override
    public Page<Rating> getRatingsByUser(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ratingRepository.findByUserId(userId, pageable);
    }
}

