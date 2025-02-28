package com.jidnivai.sdcian.sdcian.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByProductId(Long productId);

    List<Rating> findByUserId(Long userId);

    Page<Rating> findByProductId(Long productId, Pageable pageable);

    Page<Rating> findByUserId(Long userId, Pageable pageable);

}
