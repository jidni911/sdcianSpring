package com.jidnivai.sdcian.sdcian.interfaces;


import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.dto.NewCommentDto;
import com.jidnivai.sdcian.sdcian.entity.Comment;
import com.jidnivai.sdcian.sdcian.entity.User;

public interface CommentServiceInt {

    Comment getComment(Long id);

    Comment saveComment(NewCommentDto newCommentDto, User user);

    Comment updateComment(Comment comment);

    void deleteComment(Long id);

    public Page<Comment> getCommentsByPost(Long postId, int page, int size);

    public Page<Comment> getCommentsByUser(Long userId, int page, int size);

    
} 