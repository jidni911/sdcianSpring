package com.jidnivai.sdcian.sdcian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
