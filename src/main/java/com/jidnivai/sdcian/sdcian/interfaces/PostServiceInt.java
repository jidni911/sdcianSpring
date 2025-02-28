package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.dto.NewPostDto;
import com.jidnivai.sdcian.sdcian.entity.Post;

public interface PostServiceInt {

    Post getPost(Long id);

    Page<Post> getPosts(int page, int size);

    Post savePost(NewPostDto newPostDto, Long userId);

    Page<Post> search(String name, int page, int size);

    void deletePost(Long id, Long userId);

    Page<Post> getPostsByUser(Long id, int page, int size);

    Post likePost(Long postId, Long id);

    void reportPost(Long id, Long userId);

}
