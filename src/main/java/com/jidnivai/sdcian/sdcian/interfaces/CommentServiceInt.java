package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.Comment;

public interface CommentServiceInt {

    Comment getComment(Long id);

    Comment saveComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(Long id);

    List<Comment> getCommentsByPost(Long postId);

    List<Comment> getCommentsByUser(Long userId);

    
} 