package com.jidnivai.sdcian.sdcian.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByNameContaining(String name, Pageable pageable);

    Page<Post> findByCreatorId(Long id, Pageable pageable);

}
