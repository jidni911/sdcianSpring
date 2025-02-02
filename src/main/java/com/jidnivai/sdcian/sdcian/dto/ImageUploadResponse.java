package com.jidnivai.sdcian.sdcian.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ImageUploadResponse {
    private Long id;
    private String url;
}
