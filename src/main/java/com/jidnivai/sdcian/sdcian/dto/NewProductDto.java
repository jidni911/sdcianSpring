package com.jidnivai.sdcian.sdcian.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewProductDto {

    private String name;

    private String description;

    private String category;

    private String brand;

    private Float price;

    private Float discountPrice;

    private String stockStatus;//Availabe, Low , Out of Stock

    private int quantity;

    
    private Long mainImageId;

    private List<Long> galleryImagesId;

    private String dimensions;
    
    private String weight;

    private List<String> tags;

    private String origin;

    private List<String> features;
}
