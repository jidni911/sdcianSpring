package com.jidnivai.sdcian.sdcian.interfaces;


import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.shop.Rating;

public interface RatingServiceInt {

    Rating getRating(Long id);

    Page<Rating> getRatings(int page, int size);

    Rating createRating(Rating rating);

    Rating updateRating(Long id, Rating rating);

    void deleteRating(Long id);

    Page<Rating> getRatingsByProduct(Long id, int page, int size);

    Page<Rating> getRatingsByUser(Long userId, int page, int size);

}
