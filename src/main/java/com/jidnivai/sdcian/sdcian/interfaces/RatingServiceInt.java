package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.Rating;

public interface RatingServiceInt {

    Rating getRating(Long id);

    List<Rating> getRatings();

    Rating createRating(Rating rating);

    Rating updateRating(Long id, Rating rating);

    void deleteRating(Long id);

    List<Rating> getRatingsByProduct(Long id);

    List<Rating> getRatingsByUser(Long userId);

}
