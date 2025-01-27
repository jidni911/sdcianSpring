package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Post;

public interface PostServiceInt {

    Post getPost(Long id);

    Page<Post> getPosts(int page, int size);

    Post savePost(Post post);

    Page<Post> search(String name, int page, int size);

    void deletePost(Long id);

    Page<Post> getPostsByUser(Long id, int page, int size);

}
