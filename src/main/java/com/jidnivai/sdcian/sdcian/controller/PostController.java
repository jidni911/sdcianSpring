package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.dto.NewPostDto;
import com.jidnivai.sdcian.sdcian.entity.Post;
import com.jidnivai.sdcian.sdcian.interfaces.PostServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostServiceInt postServiceInt;

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postServiceInt.getPost(id);
    }

    @GetMapping
    public Page<Post> getPosts(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return postServiceInt.getPosts(page, size);

        } catch (Exception e) {
            System.out.println("PostController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Post savePost(@RequestBody NewPostDto newPostDto, @AuthenticationPrincipal UserDetailsImpl user) {
        // System.out.println(newPostDto);
        // return null;

        try {
            return postServiceInt.savePost(newPostDto, user.getUser());

        } catch (Exception e) {
            System.out.println("PostController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        try {
            return updatePost(id, post);

        } catch (Exception e) {
            System.out.println("PostController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/search/{name}")
    public Page<Post> search(@PathVariable String name, @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return postServiceInt.search(name, page, size);

        } catch (Exception e) {
            System.out.println("PostController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/user/{id}")
    public Page<Post> getPostsByUser(@PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return postServiceInt.getPostsByUser(id, page, size);

        } catch (Exception e) {
            System.out.println("PostController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/like/{postId}")
    public Post likePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return postServiceInt.likePost(postId, user.getUser());

        } catch (Exception e) {
            System.out.println("PostController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            postServiceInt.deletePost(id, user.getUser());

        } catch (Exception e) {
            System.out.println("PostController: " + e.getMessage());
            // return null;
        }
    }

    @GetMapping("report/{id}")
    public void report(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            postServiceInt.reportPost(id, user.getUser());

        } catch (Exception e) {
            System.out.println("PostController: " + e.getMessage());
            // return null;
        }
    }

}
