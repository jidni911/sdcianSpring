package com.jidnivai.sdcian.sdcian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.entity.Comment;
import com.jidnivai.sdcian.sdcian.interfaces.CommentServiceInt;

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
    public Comment saveComment(@RequestBody Comment comment) {
        return commentServiceInt.saveComment(comment);
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
    public List<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentServiceInt.getCommentsByPost(postId);
    }

    @GetMapping("/user/{userId}")
    public List<Comment> getCommentsByUser(@PathVariable Long userId) {
        return commentServiceInt.getCommentsByUser(userId);
    }

}
