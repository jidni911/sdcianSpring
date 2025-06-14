package com.jidnivai.sdcian.sdcian.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.dto.NewCommentDto;
import com.jidnivai.sdcian.sdcian.entity.Comment;
import com.jidnivai.sdcian.sdcian.interfaces.CommentServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentServiceInt commentServiceInt;

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id) {
        try {
            return commentServiceInt.getComment(id);
        } catch (Exception e) {
            System.out.println("CommentController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Comment saveComment(
        @RequestBody NewCommentDto newCommentDto,
        @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return commentServiceInt.saveComment(newCommentDto, user.getUser());
        } catch (Exception e) {
            System.out.println("CommentController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        try {
            comment.setId(id);
            return commentServiceInt.updateComment(comment);
        } catch (Exception e) {
            System.out.println("CommentController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        try {
            commentServiceInt.deleteComment(id);
        } catch (Exception e) {
            System.out.println("CommentController: " + e.getMessage());
        }
    }

    @GetMapping("/post/{postId}")
    public Page<Comment> getCommentsByPost(
        @PathVariable Long postId,
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "10") int size
        ) {
        try {
            return commentServiceInt.getCommentsByPost(postId, page, size);
        } catch (Exception e) {
            System.out.println("CommentController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/user/{userId}")
    public Page<Comment> getCommentsByUser(
        @PathVariable Long userId,
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return commentServiceInt.getCommentsByUser(userId, page, size);
        } catch (Exception e) {
            System.out.println("CommentController: " + e.getMessage());
            return null;
        }
    }

}

