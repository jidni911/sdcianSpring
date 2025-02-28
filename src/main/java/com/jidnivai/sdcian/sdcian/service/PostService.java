package com.jidnivai.sdcian.sdcian.service;

import com.jidnivai.sdcian.sdcian.dto.NewPostDto;
import com.jidnivai.sdcian.sdcian.entity.Post;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.PostServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ImageRepository;
import com.jidnivai.sdcian.sdcian.repository.PostRepository;
import com.jidnivai.sdcian.sdcian.repository.ProductRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;
import com.jidnivai.sdcian.sdcian.repository.VideoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService implements PostServiceInt {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public Post savePost(NewPostDto newPostDto, Long userId) {
        Post post = new Post();
        BeanUtils.copyProperties(newPostDto, post);
        post.setCreator(userRepository.findById(userId).orElseThrow());
        post.setPostImage(imageRepository.findAllById(newPostDto.getPostImage()));
        post.setPostVideo(videoRepository.findAllById(newPostDto.getPostVideo()));
        post.setProducts(productRepository.findAllById(newPostDto.getProducts()));
        if (newPostDto.getSharedPostId() != null) {
            post.setSharedPost(postRepository.findById(newPostDto.getSharedPostId()).orElse(null));

        }
        // System.out.println(post);

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id, Long userId) {
        Post post = postRepository.findById(id).orElseThrow();
        if (post.getCreator().getId() != userId) {
            return;
        }
        post.setDeleted(true);
        postRepository.save(post);
    }

    @Override
    public Page<Post> getPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAllByIsDeleted(false, pageable);//TODO set other method for is Deleted
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

    @Override
    public Post likePost(Long postId, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        if (post.getLikers().contains(user)) {
            post.getLikers().remove(user);
        } else {
            post.getLikers().add(user);
        }
        return postRepository.save(post);
    }

    @Override
    public void reportPost(Long id, Long userId) {
        Post post = postRepository.findById(id).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        post.getReporter().add(user);
        postRepository.save(post);
    }
}
