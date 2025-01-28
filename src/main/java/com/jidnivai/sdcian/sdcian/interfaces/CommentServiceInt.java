package com.jidnivai.sdcian.sdcian.interfaces;


import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Comment;

public interface CommentServiceInt {

    Comment getComment(Long id);

    Comment saveComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(Long id);

    public Page<Comment> getCommentsByPost(Long postId, int page, int size);

    public Page<Comment> getCommentsByUser(Long userId, int page, int size);

    
} 