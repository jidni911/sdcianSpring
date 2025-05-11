package com.jidnivai.sdcian.sdcian.service;

import com.jidnivai.sdcian.sdcian.dto.NewCommentDto;
import com.jidnivai.sdcian.sdcian.entity.Comment;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.CommentServiceInt;
import com.jidnivai.sdcian.sdcian.repository.CommentRepository;
import com.jidnivai.sdcian.sdcian.repository.ImageRepository;
import com.jidnivai.sdcian.sdcian.repository.PostRepository;
import com.jidnivai.sdcian.sdcian.repository.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CommentService implements CommentServiceInt {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment saveComment(NewCommentDto newCommentDto, User user) {
        Comment comment = new Comment();
        comment.setCommentText(newCommentDto.getCommentText());
        if (newCommentDto.getImageIds() != null && newCommentDto.getImageIds().size() > 0) {
            comment.setImage(imageRepository.findAllById(newCommentDto.getImageIds()));
        }
        if (newCommentDto.getVideoIds() != null && newCommentDto.getVideoIds().size() > 0) {
            comment.setVideo(videoRepository.findAllById(newCommentDto.getVideoIds()));
        }

        if (newCommentDto.getParentId() != null) {
            comment.setParent(commentRepository.findById(newCommentDto.getParentId()).orElse(null));
        }else if(newCommentDto.getPostId() != null) {
            comment.setPost(postRepository.findById(newCommentDto.getPostId()).orElse(null));
        } else {
            return null;
        }
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> getCommentsByPost(Long postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return commentRepository.findByPostId(postId, pageable);
    }

    @Override
    public Page<Comment> getCommentsByUser(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return commentRepository.findByUserId(userId, pageable);
    }

  
   
}

