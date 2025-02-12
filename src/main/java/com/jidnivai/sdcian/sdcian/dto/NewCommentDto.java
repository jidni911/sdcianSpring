package com.jidnivai.sdcian.sdcian.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewCommentDto {

    private String commentText;

    private List<Long> imageIds;
    private List<Long> videoIds;

    private Long parentId;

    private Long postId;
}
