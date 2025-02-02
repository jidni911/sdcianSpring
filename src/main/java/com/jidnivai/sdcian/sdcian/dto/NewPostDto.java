package com.jidnivai.sdcian.sdcian.dto;

import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class NewPostDto {

    private String postText;

    private String location;

    private List<Long> postImage;

    private List<Long> postVideo;

    private Long sharedPostId;

    private List<Long> products;

    private boolean isPublic = true;
}
