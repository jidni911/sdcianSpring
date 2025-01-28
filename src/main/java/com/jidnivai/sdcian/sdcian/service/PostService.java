package com.jidnivai.sdcian.sdcian.service;

import com.jidnivai.sdcian.sdcian.entity.Post;
import com.jidnivai.sdcian.sdcian.interfaces.PostServiceInt;
import com.jidnivai.sdcian.sdcian.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PostService implements PostServiceInt {

    @Autowired
    private PostRepository postRepository;

   

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> getPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> search(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByPostTextContaining(name, pageable);
    }

    @Override
    public Page<Post> getPostsByUser(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByCreatorId(id, pageable);
    }
}

