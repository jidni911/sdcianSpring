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
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentServiceInt commentServiceInt;

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id) {
        return commentServiceInt.getComment(id);
    }

    @PostMapping
    public Comment saveComment(
        @RequestBody NewCommentDto newCommentDto,
        @AuthenticationPrincipal UserDetailsImpl user) {
        return commentServiceInt.saveComment(newCommentDto, user.getId());
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        comment.setId(id);
        return commentServiceInt.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentServiceInt.deleteComment(id);
    }

    @GetMapping("/post/{postId}")
    public Page<Comment> getCommentsByPost(
        @PathVariable Long postId,
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "10") int size
        ) {
        return commentServiceInt.getCommentsByPost(postId, page, size);
    }

    @GetMapping("/user/{userId}")
    public Page<Comment> getCommentsByUser(
        @PathVariable Long userId,
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "10") int size) {
        return commentServiceInt.getCommentsByUser(userId, page, size);
    }

}
