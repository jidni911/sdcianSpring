package com.jidnivai.sdcian.sdcian.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    // Adjusted to search by postText instead of name
    Page<Post> findByPostTextContaining(String postText, Pageable pageable);

    // Adjusted to search by creator's ID
    Page<Post> findByCreatorId(Long creatorId, Pageable pageable);

    Page<Post> findAllByIsDeleted(boolean b, Pageable pageable);

}
