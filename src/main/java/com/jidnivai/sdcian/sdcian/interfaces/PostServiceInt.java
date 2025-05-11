package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.dto.NewPostDto;
import com.jidnivai.sdcian.sdcian.entity.Post;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface PostServiceInt {

    Post getPost(Long id);

    Page<Post> getPosts(int page, int size);

    Post savePost(NewPostDto newPostDto, User user);

    Page<Post> search(String name, int page, int size);

    void deletePost(Long id, User user);

    Page<Post> getPostsByUser(Long id, int page, int size);

    Post likePost(Long postId, User user);

    void reportPost(Long id, User user);

}
